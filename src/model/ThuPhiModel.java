package model;


import java.sql.Date;

public class ThuPhiModel {

    private int idHoKhau;
    private int idKhoanThu;
    private double soTienNop;
    private Date thoiGianNop;

    public ThuPhiModel(int idHoKhau, int idKhoanThu, double soTienNop, Date thoiGianNop) {
        this.idHoKhau = idHoKhau;
        this.idKhoanThu = idKhoanThu;
        this.soTienNop = soTienNop;
        this.thoiGianNop = thoiGianNop;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public int getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(int idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public double getSoTienNop() {
        return soTienNop;
    }

    public void setSoTienNop(double soTienNop) {
        this.soTienNop = soTienNop;
    }

    public Date getThoiGianNop() {
        return thoiGianNop;
    }

    public void setThoiGianNop(Date thoiGianNop) {
        this.thoiGianNop = thoiGianNop;
    }
}
