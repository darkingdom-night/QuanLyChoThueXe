package QuanLyChoThueXe;

import java.util.Scanner;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String cccd;
    private String sdt;

    public KhachHang() {}
    public KhachHang(String maKH, String tenKH, String cccd, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cccd = cccd                ;
        this.sdt = sdt;
    }
    
    public String getMaKH() {
        return maKH;
    }
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    public String getTenKH() {
        return tenKH;
    }
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
    public String getCccd() {
        return cccd;
    }
    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mã KH: "); maKH = sc.nextLine();
        System.out.print("Tên KH: "); tenKH = sc.nextLine();
        System.out.print("CCCD: "); cccd = sc.nextLine();
        System.out.print("SĐT: "); sdt = sc.nextLine();
    }

    @Override
    public String toString() {
        return "[" + maKH + "] " + tenKH + ", CCCD: " + cccd + ", SĐT: " + sdt;
    }
}
