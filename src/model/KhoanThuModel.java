package model;

import java.sql.Date;

public class KhoanThuModel {

    private int id;
    private String tenKhoanThu;
    private boolean batBuoc;
    private double soTien;
    private Date thoiGian;
    private Date hanNop;

    public KhoanThuModel(String tenKhoanThu, Date hanNop){
        this.tenKhoanThu = tenKhoanThu;
        this.hanNop = hanNop;
    }
    public KhoanThuModel(String tenKhoanThu, double soTien, Date thoiGian, Date hanNop) {
        this.tenKhoanThu = tenKhoanThu;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.hanNop = hanNop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public boolean isBatBuoc() {
        return batBuoc;
    }

    public void setBatBuoc(boolean batBuoc) {
        this.batBuoc = batBuoc;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getHanNop() {
        return hanNop;
    }

    public void setHanNop(Date hanNop) {
        this.hanNop = hanNop;
    }
}
