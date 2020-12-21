package datatable;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Date;

public class ThuPhiData {
    private final StringProperty idHoKhau;
    private final StringProperty stt;
    private final StringProperty hoTenChuHo;
    private final StringProperty soThanhVien;
    private final StringProperty soTienPhaiNop;
    private final StringProperty diaChiHienNay;
    private final StringProperty thoiGianNop;
    private final SimpleDoubleProperty soTienNop;
    private JFXDatePicker selected;

    public ThuPhiData(String idHoKhau, String stt, String hoTenChuHo, String soThanhVien, String soTienPhaiNop, String diaChiHienNay, String thoiGianNop, Double soTienNop){
        this.idHoKhau = new SimpleStringProperty(idHoKhau);
        this.stt = new SimpleStringProperty(stt);
        this.hoTenChuHo = new SimpleStringProperty(hoTenChuHo);
        this.soThanhVien = new SimpleStringProperty(soThanhVien);
        this.soTienPhaiNop = new SimpleStringProperty(soTienPhaiNop);
        this.diaChiHienNay = new SimpleStringProperty(diaChiHienNay);
        this.thoiGianNop = new SimpleStringProperty(thoiGianNop);
        this.soTienNop = new SimpleDoubleProperty(soTienNop);
        this.selected = new JFXDatePicker();
    }

    public String getStt() {
        return stt.get();
    }

    public StringProperty sttProperty() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt.set(stt);
    }

    public String getHoTenChuHo() {
        return hoTenChuHo.get();
    }

    public StringProperty hoTenChuHoProperty() {
        return hoTenChuHo;
    }

    public void setHoTenChuHo(String hoTenChuHo) {
        this.hoTenChuHo.set(hoTenChuHo);
    }

    public String getSoThanhVien() {
        return soThanhVien.get();
    }

    public StringProperty soThanhVienProperty() {
        return soThanhVien;
    }

    public void setSoThanhVien(String soThanhVien) {
        this.soThanhVien.set(soThanhVien);
    }

    public String getSoTienPhaiNop() {
        return soTienPhaiNop.get();
    }

    public StringProperty soTienPhaiNopProperty() {
        return soTienPhaiNop;
    }

    public void setSoTienPhaiNop(String soTienPhaiNop) {
        this.soTienPhaiNop.set(soTienPhaiNop);
    }

    public String getDiaChiHienNay() {
        return diaChiHienNay.get();
    }

    public StringProperty diaChiHienNayProperty() {
        return diaChiHienNay;
    }

    public void setDiaChiHienNay(String diaChiHienNay) {
        this.diaChiHienNay.set(diaChiHienNay);
    }

    public double getSoTienNop() {
        return soTienNop.get();
    }

    public SimpleDoubleProperty soTienNopProperty() {
        return soTienNop;
    }

    public void setSoTienNop(double soTienNop) {
        this.soTienNop.set(soTienNop);
    }

    public String getIdHoKhau() {
        return idHoKhau.get();
    }

    public StringProperty idHoKhauProperty() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau.set(idHoKhau);
    }

    public String getThoiGianNop() {
        return thoiGianNop.get();
    }

    public StringProperty thoiGianNopProperty() {
        return thoiGianNop;
    }

    public void setThoiGianNop(String thoiGianNop) {
        this.thoiGianNop.set(thoiGianNop);
    }

    public JFXDatePicker getSelected() {
        return selected;
    }

    public void setSelected(JFXDatePicker selected) {
        this.selected = selected;
    }
}
