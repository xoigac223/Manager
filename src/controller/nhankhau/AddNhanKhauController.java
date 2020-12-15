package controller.nhankhau;

import bean.NhanKhauBean;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.NhanKhauModel;
import service.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddNhanKhauController implements Initializable {
    NhanKhauBean nhanKhauBean = new NhanKhauBean();
    NhanKhauService nhanKhauService = new NhanKhauService();

    @FXML
    JFXTextField hoVaTen, nguyenQuan, ngaySinh, danToc, cmt, noiThuongTru, hocVan, ngoaiNgu,
                    ngheNghiep, bietDanh, tonGiao, quocTich, hoChieu, diaChiHienTai, trinhDoChuyenMon,
                    tiengDanToc, noiLamViec;
    @FXML
    JFXComboBox gioiTinh;

    @FXML
    JFXDatePicker date_picker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gioiTinh.getItems().add("Nam");
        gioiTinh.getItems().add("Nữ");
//        nav.harusAngka(uang_keluar);
//        detail_uang_keluar.setOnKeyPressed(event->{if(event.getCode()== KeyCode.ENTER){inputMethod();}});
//        uang_keluar.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
    }

    @FXML
    private void insertClicked(ActionEvent event){
        input();
    }

    @FXML
    private void khacClicked(ActionEvent event){
        try {
            Stage tieusuStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane khacRoot = (Pane)loader.load(getClass().getResource("/view/nhankhau/tieusu.fxml").openStream());
            Scene scene = new Scene(khacRoot);
            tieusuStage.setScene(scene);
            tieusuStage.setTitle("Thông tin khác");
            tieusuStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clear(){
        hoVaTen.setText("");
        nguyenQuan.setText("");
        danToc.setText("");
        cmt.setText("");
        noiThuongTru.setText("");
        hocVan.setText("");
        ngoaiNgu.setText("");
        ngheNghiep.setText("");
        bietDanh.setText("");
        tonGiao.setText("");
        quocTich.setText("");
        hoChieu.setText("");
        diaChiHienTai.setText("");
        trinhDoChuyenMon.setText("");
        tiengDanToc.setText("");
        noiLamViec.setText("");
        hoVaTen.requestFocus();
    }

    @FXML
    private void dateClicked(ActionEvent event){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = sdf.format(Date.valueOf(date_picker.getValue()));
        ngaySinh.setText(dateText);
    }

    void input(){
        String gioi_tinh = (String)gioiTinh.getValue();
        if(hoVaTen.getText().equals("")||nguyenQuan.getText().equals("")||ngaySinh.getText().equals("")||
        danToc.getText().equals("")||cmt.getText().equals("")||noiThuongTru.getText().equals("")||
        hocVan.getText().equals("")||ngoaiNgu.getText().equals("")||ngheNghiep.getText().equals("")||
        tonGiao.getText().equals("")||quocTich.getText().equals("")||diaChiHienTai.getText().equals("")||
        trinhDoChuyenMon.getText().equals("")||tiengDanToc.getText().equals("")||noiLamViec.getText().equals("")||
        gioi_tinh.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("Nhập đầy đủ thông tin có dấu *");
            alert.showAndWait();
        } else {
            NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
            Calendar cal = Calendar.getInstance();
            java.sql.Date ngayTao = new java.sql.Date(cal.getTime().getTime());
            nhanKhauModel.setSoHoChieu(hoChieu.getText());
            nhanKhauModel.setBietDanh(bietDanh.getText());
            nhanKhauModel.setHoTen(hoVaTen.getText());
            nhanKhauModel.setNguyenQuan(nguyenQuan.getText());
            LocalDate localDate = date_picker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = Date.from(instant);
            java.sql.Date ns = new java.sql.Date(date.getTime());
            nhanKhauModel.setDanToc(danToc.getText());
            nhanKhauModel.setNoiThuongTru(noiThuongTru.getText());
            nhanKhauModel.setTrinhDoHocVan(hocVan.getText());
            nhanKhauModel.setTrinhDoNgoaiNgu(ngoaiNgu.getText());
            nhanKhauModel.setNgheNghiep(ngheNghiep.getText());
            nhanKhauModel.setTonGiao(tonGiao.getText());
            nhanKhauModel.setQuocTich(quocTich.getText());
            nhanKhauModel.setDiaChiHienNay(diaChiHienTai.getText());
            nhanKhauModel.setTrinhDoChuyenMon(trinhDoChuyenMon.getText());
            nhanKhauModel.setBietTiengDanToc(tiengDanToc.getText());
            nhanKhauModel.setNoiLamViec(noiLamViec.getText());
            nhanKhauModel.setGioiTinh(gioi_tinh);
            nhanKhauModel.setIdNguoiTao(1);

            int rs = 0;
            try {
                rs = nhanKhauService.addNhanKhau(nhanKhauModel.getHoTen(), nhanKhauModel.getBietDanh(), ns, nhanKhauModel.getGioiTinh(), nhanKhauModel.getNguyenQuan(), nhanKhauModel.getDanToc(), nhanKhauModel.getTonGiao(), nhanKhauModel.getQuocTich(), nhanKhauModel.getSoHoChieu(), nhanKhauModel.getNoiThuongTru(), nhanKhauModel.getDiaChiHienNay(),
                        nhanKhauModel.getTrinhDoHocVan(), nhanKhauModel.getTrinhDoChuyenMon(), nhanKhauModel.getBietTiengDanToc(), nhanKhauModel.getTrinhDoNgoaiNgu(), nhanKhauModel.getNgheNghiep(), nhanKhauModel.getNoiLamViec(), ngayTao, nhanKhauModel.getIdNguoiTao());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(rs == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Thành công");
                alert.setContentText("Thêm thành công");
                alert.showAndWait();
                clear();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Thất bại");
                alert.setContentText("Thêm thất bại! Thử lại!");
                alert.showAndWait();
                clear();
            }
        }
    }
}
