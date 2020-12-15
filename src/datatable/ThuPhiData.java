package datatable;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThuPhiData {
    private final StringProperty stt;
    private final StringProperty hoTenChuHo;
    private final StringProperty soThanhVien;
    private final StringProperty soTienPhaiNop;
    private final StringProperty soTienDaNop;
    private final StringProperty thoiGianNop;
    private JFXCheckBox selected;

    public ThuPhiData(String stt, String hoTenChuHo, String soThanhVien,  String soTienPhaiNop, String soTienDaNop, String thoiGianNop){
        this.stt = new SimpleStringProperty(stt);
        this.hoTenChuHo = new SimpleStringProperty(hoTenChuHo);
        this.soThanhVien = new SimpleStringProperty(soThanhVien);
        this.soTienPhaiNop = new SimpleStringProperty(soTienPhaiNop);
        this.soTienDaNop = new SimpleStringProperty(soTienDaNop);
        this.thoiGianNop = new SimpleStringProperty(thoiGianNop);
        this.selected = new JFXCheckBox();
        if (thoiGianNop != null) selected.setSelected(true);
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

    public String getSoTienDaNop() {
        return soTienDaNop.get();
    }

    public StringProperty soTienDaNopProperty() {
        return soTienDaNop;
    }

    public void setSoTienDaNop(String soTienDaNop) {
        this.soTienDaNop.set(soTienDaNop);
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

    public JFXCheckBox getSelected() {
        return selected;
    }

    public void setSelected(JFXCheckBox selected) {
        this.selected = selected;
    }
}
