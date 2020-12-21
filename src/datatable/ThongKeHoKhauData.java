package datatable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThongKeHoKhauData {
    private final StringProperty tenKhoanThu;
    private final StringProperty ngayNop;
    private final StringProperty soTien;

    public ThongKeHoKhauData(String tenKhoanThu, String ngayNop, String soTien){
        this.tenKhoanThu = new SimpleStringProperty(tenKhoanThu);
        this.ngayNop = new SimpleStringProperty(ngayNop);
        this.soTien = new SimpleStringProperty(soTien);
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

    public String getNgayNop() {
        return ngayNop.get();
    }

    public StringProperty ngayNopProperty() {
        return ngayNop;
    }

    public void setNgayNop(String ngayNop) {
        this.ngayNop.set(ngayNop);
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
}
