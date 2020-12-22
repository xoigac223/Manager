package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.KhoanThuModel;
import service.HoKhauService;
import service.KhoanThuService;
import service.NhanKhauService;
import service.ThuPhiService;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class HomeController implements Initializable {

    @FXML
    private Label lbSoNhanKhau;

    @FXML
    private Label lbSoHoKhau;

    @FXML
    private Label lbTamTru;

    @FXML
    private Label lbTamVang;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Label tenKhoanThuTop1;

    @FXML
    private Label hanNopTop1;

    @FXML
    private Label tenKhoanThuTop2;

    @FXML
    private Label hanNopTop2;

    @FXML
    private Label tenKhoanThuTop3;

    @FXML
    private Label hanNopTop3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setBarChart();
            setData();
            setKhoanThuSapHetHan();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setBarChart() throws SQLException {

        XYChart.Series dataSeries = new XYChart.Series();

        ThuPhiService thuPhiService = new ThuPhiService();
        HashMap data = thuPhiService.getTop3();

        Set set= data.entrySet();//Converting to Set so that we can traverse
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            Map.Entry entry=(Map.Entry)itr.next();
            dataSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(dataSeries);
    }

    public void setData() throws SQLException {
        HoKhauService hoKhauService = new HoKhauService();
        NhanKhauService nhanKhauService = new NhanKhauService();
        lbSoHoKhau.setText(String.valueOf(hoKhauService.tinhSoHoKhau()));
        lbSoNhanKhau.setText(String.valueOf(nhanKhauService.tinhSoNhanKhau()));
        lbTamVang.setText(String.valueOf(nhanKhauService.tinhSoNhanKhauTamVang()));
        lbTamTru.setText(String.valueOf(nhanKhauService.tinhSoNhanKhauTamTru()));
    }

    public void setKhoanThuSapHetHan() throws SQLException {
        KhoanThuService khoanThuService = new KhoanThuService();
        ArrayList<KhoanThuModel> khoanThuModelArrayList = khoanThuService.getTop3();
        tenKhoanThuTop1.setText(khoanThuModelArrayList.get(0).getTenKhoanThu());
        hanNopTop1.setText(String.valueOf(khoanThuModelArrayList.get(0).getHanNop()));
        tenKhoanThuTop2.setText(khoanThuModelArrayList.get(1).getTenKhoanThu());
        hanNopTop2.setText(String.valueOf(khoanThuModelArrayList.get(1).getHanNop()));
        tenKhoanThuTop3.setText(khoanThuModelArrayList.get(2).getTenKhoanThu());
        hanNopTop3.setText(String.valueOf(khoanThuModelArrayList.get(2).getHanNop()));
    }
}
