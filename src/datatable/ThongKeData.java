package datatable;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThongKeData {
    private final StringProperty id;
    private final StringProperty stt;
    private final StringProperty hoTenChuHo;
    private final StringProperty diaChiHienTai;
    private final StringProperty tongSoTien;

    public ThongKeData(String id, String stt, String hoTenChuHo, String diaChiHienNay, String tongSoTien){
        this.id = new SimpleStringProperty(id);
        this.stt = new SimpleStringProperty(stt);
        this.hoTenChuHo = new SimpleStringProperty(hoTenChuHo);
        this.diaChiHienTai = new SimpleStringProperty(diaChiHienNay);
        this.tongSoTien = new SimpleStringProperty(tongSoTien);
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

    public String getHoTenChuHo() {
        return hoTenChuHo.get();
    }

    public StringProperty hoTenChuHoProperty() {
        return hoTenChuHo;
    }

    public void setHoTenChuHo(String hoTenChuHo) {
        this.hoTenChuHo.set(hoTenChuHo);
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai.get();
    }

    public StringProperty diaChiHienTaiProperty() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai.set(diaChiHienTai);
    }

    public String getTongSoTien() {
        return tongSoTien.get();
    }

    public StringProperty tongSoTienProperty() {
        return tongSoTien;
    }

    public void setTongSoTien(String tongSoTien) {
        this.tongSoTien.set(tongSoTien);
    }
}
