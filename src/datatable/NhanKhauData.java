package datatable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NhanKhauData {
    private final StringProperty id;
    private final StringProperty stt;
    private final StringProperty hoTen;
    private final StringProperty ngaySinh;
    private final StringProperty gioiTinh;
    private final StringProperty diaChiHienNay;

    public NhanKhauData(String id, String STT, String hoten, String ngaysinh, String gioitinh, String diachihiennay) {
        this.id = new SimpleStringProperty(id);
        this.stt = new SimpleStringProperty(STT);
        hoTen = new SimpleStringProperty(hoten);
        ngaySinh = new SimpleStringProperty(ngaysinh);
        gioiTinh = new SimpleStringProperty(gioitinh);
        diaChiHienNay = new SimpleStringProperty(diachihiennay);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    public String getHoTen() {
        return hoTen.get();
    }

    public StringProperty hoTenProperty() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public String getNgaySinh() {
        return ngaySinh.get();
    }

    public StringProperty ngaySinhProperty() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public String getGioiTinh() {
        return gioiTinh.get();
    }

    public StringProperty gioiTinhProperty() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh.set(gioiTinh);
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
}

