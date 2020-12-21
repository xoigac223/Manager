package controller.thongke;

import datatable.KhoanThuData;
import datatable.ThongKeData;
import datatable.ThongKeHoKhauData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ThongKeService;

import javax.swing.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKeTheoHoKhauController implements Initializable {
    private String idHoKhau;
    private String hoTenChuHo;
    private ThongKeService thongKeService = new ThongKeService();
    private ObservableList<ThongKeHoKhauData> data;

    @FXML
    private Label labelHoTenChuHo;

    @FXML
    private TableView<ThongKeHoKhauData> table;

    @FXML
    private TableColumn<ThongKeHoKhauData, String> tenKhoanThuColumn;

    @FXML
    private TableColumn<ThongKeHoKhauData, String> ngayNopColumn;

    @FXML
    private TableColumn<ThongKeHoKhauData, String> soTienColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelHoTenChuHo.setText(hoTenChuHo);
        loadTable();
    }

    public ThongKeTheoHoKhauController(String idHoKhau, String hoTenChuHo){
        this.idHoKhau = idHoKhau;
        this.hoTenChuHo = hoTenChuHo;
    }

    private void loadTable() {
        try {
            data = FXCollections.observableArrayList();
            data = thongKeService.getThongKeTheoHoKhau(idHoKhau);
            tenKhoanThuColumn.setCellValueFactory(new PropertyValueFactory<ThongKeHoKhauData, String>("tenKhoanThu"));
            ngayNopColumn.setCellValueFactory(new PropertyValueFactory<ThongKeHoKhauData, String>("ngayNop"));
            soTienColumn.setCellValueFactory(new PropertyValueFactory<ThongKeHoKhauData, String>("soTien"));
            table.setItems(null);
            table.setItems(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
