package QuanLyChoThueXe;

import java.util.Scanner;

public class XeChoHang extends Xe {
    private double taiTrong;

    public XeChoHang() {}
    public XeChoHang(String maXe, String tenXe, String bienSo, double giaThue, int soLuong, String viTri, double taiTrong) {
        super(maXe, tenXe, bienSo, giaThue, soLuong, viTri);
        this.taiTrong = taiTrong;
    }
    
    public double getTaiTrong() { 
        return taiTrong; 
    }
    public void setTaiTrong(double taiTrong) { 
        this.taiTrong = taiTrong; 
    }

    @Override
    public double tinhTienThue(int soNgay) { return giaThue * soNgay; }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Tải trọng (tấn): "); taiTrong = sc.nextDouble(); sc.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + ", Loại: Chở hàng, Tải trọng: " + taiTrong + " tấn";
    }
}
