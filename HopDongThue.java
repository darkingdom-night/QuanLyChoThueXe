package QuanLyChoThueXe;

import java.util.Date;

public class HopDongThue {
    private String maHD;
    private KhachHang kh;
    private Xe xe;
    private int soNgay;
    private Date ngayThue;

    public HopDongThue() {}
    public HopDongThue(String maHD, KhachHang kh, Xe xe, int soNgay) {
        this.maHD = maHD;
        this.kh = kh;
        this.xe = xe;
        this.soNgay = soNgay;
        this.ngayThue = new Date();
    }
    
    public String getMaHD() {
        return maHD;
    }
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }
    public KhachHang getKh() {
        return kh;
    }
    public void setKh(KhachHang kh) {
        this.kh = kh;
    }
    public Xe getXe() {
        return xe;
    }
    public void setXe(Xe xe) {
        this.xe = xe;
    }
    public int getSoNgay() {
        return soNgay;
    }
    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }
    public Date getNgayThue() {
        return ngayThue;
    }
    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }
    
    @Override
    public String toString() {
        return "HĐ[" + maHD + "] Ngày: " + ngayThue +
                "\nKhách hàng: " + kh +
                "\nXe thuê: " + xe +
                "\nSố ngày: " + soNgay +
                ", Tổng tiền: " + xe.tinhTienThue(soNgay);
    }
}
