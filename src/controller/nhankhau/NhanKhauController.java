package controller.nhankhau;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import datatable.NhanKhauData;
import service.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class NhanKhauController implements Initializable {

    @FXML
    private TableView<NhanKhauData> userDataTableView;

    @FXML
    private TableColumn<NhanKhauData, String> idColumn;

    @FXML
    private TableColumn<NhanKhauData, String> sttColumn;

    @FXML
    private TableColumn<NhanKhauData, String> hotenColumn;

    @FXML
    private TableColumn<NhanKhauData, String> gioiTinhColumn;

    @FXML
    private TableColumn<NhanKhauData, String> ngaySinhColumn;

    @FXML
    private TableColumn<NhanKhauData, String> diaChiColumn;

    @FXML
    private AnchorPane unclear, loadPane;

    @FXML
    private StackPane trans;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
    }

    private ObservableList<NhanKhauData> data;
    private NhanKhauService nhanKhauTable = new NhanKhauService();

    public void loadTable(){
        try {
            data = FXCollections.observableArrayList();
            data = nhanKhauTable.getAllNhanKhau();
        } catch (SQLException e){
            e.printStackTrace();
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("id"));
        sttColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("stt"));
        hotenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("ngaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("gioiTinh"));
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauData, String>("diaChiHienNay"));
        userDataTableView.setItems(null);
        userDataTableView.setItems(data);
    }

    @FXML
    private void addClicked() throws IOException {
        unclear.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        FXMLLoader addNhanKhauLoader = new FXMLLoader();
        AnchorPane pane = (AnchorPane)addNhanKhauLoader.load(getClass().getResource("/view/nhankhau/add_nhan_khau.fxml").openStream());
        loadPane.getChildren().setAll(pane);
    }
    @FXML
    private void closeClicked(ActionEvent event){
        loadTable();
        unclear.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        //clearParameter();
    }
}