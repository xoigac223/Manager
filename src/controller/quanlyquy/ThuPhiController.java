package controller.quanlyquy;


import com.jfoenix.controls.JFXCheckBox;
import datatable.ThuPhiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.KhoanThuService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThuPhiController implements Initializable {
    @FXML
    private Label labelName;

    @FXML
    private TableView thuPhiTableView;

    @FXML
    TableColumn<ThuPhiData, String> sttColumn;

    @FXML
    TableColumn<ThuPhiData, String> tenChuHoColumn;

    @FXML
    TableColumn<ThuPhiData, String> soThanhVienColumn;

    @FXML
    TableColumn<ThuPhiData, String> soTienPhaiNopColumn;

    @FXML
    TableColumn<ThuPhiData, String> soTienDaNopColumn;

    @FXML
    TableColumn<ThuPhiData, String> thoiGianNopColumn;

    @FXML
    TableColumn<ThuPhiData, JFXCheckBox> selectedColoum;

    @FXML
    JFXCheckBox cboxSelectAll;

    @FXML
    public void saveClicked(){
    }
    @FXML
    public void rowClicked(){

    }
    @FXML
    public void refreshClicked(){
        loadTable();
    }
    private ObservableList<ThuPhiData> data;
    private KhoanThuService khoanThuService = new KhoanThuService();
    private String id ,stt, tenKhoanThu , batBuoc , soTien , thoiGian , hanNop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTable();
        if (batBuoc.equals("1")){
            labelName.setText(" " + tenKhoanThu + " - Có bắt buộc");
        } else {
            labelName.setText(" " + tenKhoanThu + " - Không bắt buộc");
        }
    }

    public ThuPhiController(String id, String stt, String tenKhoanThu, String batBuoc, String soTien, String thoiGian, String hanNop) {
        this.id = id;
        this.stt = stt;
        this.tenKhoanThu = tenKhoanThu;
        this.batBuoc = batBuoc;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.hanNop = hanNop;
    }

    public void loadTable(){
        try {
            data = FXCollections.observableArrayList();
            data = khoanThuService.getThuPhi(id, soTien);
        } catch (SQLException e){
            e.printStackTrace();
        }
        sttColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("stt"));
        tenChuHoColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("hoTenChuHo"));
        soThanhVienColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("soThanhVien"));
        soTienPhaiNopColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("soTienPhaiNop"));
        soTienDaNopColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("soTienDaNop"));
        thoiGianNopColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("thoiGianNop"));
        selectedColoum.setCellValueFactory(new PropertyValueFactory<ThuPhiData, JFXCheckBox>("selected"));

        thoiGianNopColumn.setOnEditCommit(
                t -> ((ThuPhiData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setThoiGianNop(t.getNewValue())
        );
        thuPhiTableView.setItems(null);
        thuPhiTableView.setItems(data);
    }


}
