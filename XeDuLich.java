package QuanLyChoThueXe;

import java.util.Scanner;

public class XeDuLich extends Xe {
    private int soCho;

    public XeDuLich() {}
    public XeDuLich(String maXe, String tenXe, String bienSo, double giaThue, int soLuong, String viTri, int soCho) {
        super(maXe, tenXe, bienSo, giaThue, soLuong, viTri);
        this.soCho = soCho;
    }
    public int getSoCho() { 
        return soCho;
    }
    public void setSoCho(int soCho) { 
        this.soCho = soCho;
    }
    @Override
    public double tinhTienThue(int soNgay) { return giaThue * soNgay; }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Số chỗ: "); soCho = sc.nextInt(); sc.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + ", Loại: Du lịch, Số chỗ: " + soCho;
    }
}
