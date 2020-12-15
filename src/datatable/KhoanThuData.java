package datatable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KhoanThuData {

    private final StringProperty id;
    private final StringProperty stt;
    private final StringProperty tenKhoanThu;
    private final StringProperty batBuoc;
    private final StringProperty soTien;
    private final StringProperty thoiGian;
    private final StringProperty hanNop;

    public KhoanThuData(String id, String stt, String tenKhoanThu, String batBuoc, String soTien, String thoiGian, String hanNop) {
        this.id = new SimpleStringProperty(id);
        this.stt = new SimpleStringProperty(stt);
        this.tenKhoanThu = new SimpleStringProperty(tenKhoanThu);
        this.batBuoc = new SimpleStringProperty(batBuoc);
        this.soTien = new SimpleStringProperty(soTien);
        this.thoiGian = new SimpleStringProperty(thoiGian);
        this.hanNop = new SimpleStringProperty(hanNop);
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

    public String getTenKhoanThu() {
        return tenKhoanThu.get();
    }

    public StringProperty tenKhoanThuProperty() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu.set(tenKhoanThu);
    }

    public String getBatBuoc() {
        return batBuoc.get();
    }

    public StringProperty batBuocProperty() {
        return batBuoc;
    }

    public void setBatBuoc(String batBuoc) {
        this.batBuoc.set(batBuoc);
    }

    public String getSoTien() {
        return soTien.get();
    }

    public StringProperty soTienProperty() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien.set(soTien);
    }

    public String getThoiGian() {
        return thoiGian.get();
    }

    public StringProperty thoiGianProperty() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian.set(thoiGian);
    }

    public String getHanNop() {
        return hanNop.get();
    }

    public StringProperty hanNopProperty() {
        return hanNop;
    }

    public void setHanNop(String hanNop) {
        this.hanNop.set(hanNop);
    }
}
