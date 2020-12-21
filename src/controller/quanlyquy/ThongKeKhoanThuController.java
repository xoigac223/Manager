package controller.quanlyquy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import service.HoKhauService;
import service.KhoanThuService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKeKhoanThuController implements Initializable {
    private String id;
    private String tenKhoanThu;
    private KhoanThuService khoanThuService = new KhoanThuService();
    private HoKhauService hoKhauService = new HoKhauService();
    @FXML
    private Label labelTenKhoanThu;

    @FXML
    private Label labelTongSoTien;

    @FXML
    private PieChart pieChart;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ThongKeKhoanThuController(String id, String tenKhoanThu){
        this.id = id;
        this.tenKhoanThu = tenKhoanThu;
    }

    public void setData() throws SQLException {
        labelTenKhoanThu.setText(tenKhoanThu);
        labelTongSoTien.setText(String.valueOf(khoanThuService.tinhTongSoTien(id)));

        int soNguoiDaNop = khoanThuService.tinhSoNguoiDaNop(id);
        int soHoKhau = hoKhauService.tinhSoHoKhau();
        PieChart.Data slice1 = new PieChart.Data("Số người đã nộp", soNguoiDaNop);
        PieChart.Data slice2 = new PieChart.Data("Số người chưa nộp", soHoKhau - soNguoiDaNop);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
    }
}
