package QuanLyChoThueXe;

import java.util.Scanner;

public abstract class Xe {
    protected String maXe;
    protected String tenXe;
    protected String bienSo;
    protected double giaThue;
    protected int soLuong;
    protected String viTri;

    public Xe() {}

    public Xe(String maXe, String tenXe, String bienSo, double giaThue, int soLuong, String viTri) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.giaThue = giaThue;
        this.soLuong = soLuong;
        this.viTri = viTri;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mã xe: "); maXe = sc.nextLine();
        System.out.print("Tên xe: "); tenXe = sc.nextLine();
        System.out.print("Biển số: "); bienSo = sc.nextLine();
        System.out.print("Giá thuê/ngày: "); giaThue = sc.nextDouble();
        System.out.print("Số lượng: "); soLuong = sc.nextInt(); sc.nextLine();
        System.out.print("Vị trí: "); viTri = sc.nextLine();
    }
    
    public abstract double tinhTienThue(int soNgay);

    public boolean conXe() { return soLuong > 0; }

    public void thue(int soNgay) {
        if (!conXe()) throw new IllegalStateException("Xe đã hết!");
        if (soNgay <= 0) throw new IllegalArgumentException("Số ngày thuê phải > 0");
        soLuong--;
        System.out.println("Thuê thành công: " + soNgay + " ngày, Tổng tiền: " + tinhTienThue(soNgay));
    }

    @Override
    public String toString() {
        return "[" + maXe + "] " + tenXe + ", Biển số: " + bienSo +
                ", Giá/ngày: " + giaThue +
                ", SL: " + soLuong +
                ", Vị trí: " + viTri;
    }
}
