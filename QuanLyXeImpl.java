package QuanLyChoThueXe;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyXeImpl implements IChucNang<Xe>, IReadWrite {
    private ArrayList<Xe> danhSachXe = new ArrayList<>();

    @Override
    public void them(Xe xe) {
        if (timKiem(xe.getMaXe()) != null) {
            System.out.println(" Mã xe đã tồn tại! Không thể thêm.");
        } else {
            danhSachXe.add(xe);
            System.out.println(" Đã thêm xe mới!");
        }
    }

    @Override
    public void xoa(String maXe) {
        Xe xe = timKiem(maXe);
        if (xe != null) {
            danhSachXe.remove(xe);
            System.out.println("🗑 Đã xoá xe " + maXe);
        } else {
            System.out.println(" Không tìm thấy xe!");
        }
    }

    @Override
    public Xe timKiem(String maXe) {
        for (Xe xe : danhSachXe)
            if (xe.getMaXe().equalsIgnoreCase(maXe))
                return xe;
        return null;
    }

    @Override
    public void hienThi() {
        if (danhSachXe.isEmpty()) System.out.println(" Chưa có xe nào.");
        else danhSachXe.forEach(System.out::println);
    }

    @Override
    public void capNhat(String maXe, Xe xeMoi) {
        for (int i = 0; i < danhSachXe.size(); i++) {
            if (danhSachXe.get(i).getMaXe().equalsIgnoreCase(maXe)) {
                danhSachXe.set(i, xeMoi);
                System.out.println(" Cập nhật xe " + maXe + " thành công!");
                return;
            }
        }
        System.out.println(" Không tìm thấy xe để cập nhật!");
    }

    public void themXeMoi() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Mã xe: ");
        String ma = sc.nextLine();

        if (timKiem(ma) != null) {
            System.out.println(" Xe đã tồn tại!");
            return;
        }

        System.out.print("Tên xe: ");
        String ten = sc.nextLine();

        System.out.print("Biển số: ");
        String bienSo = sc.nextLine();

        System.out.print("Giá thuê: ");
        double gia = sc.nextDouble();

        System.out.print("Số lượng: ");
        int soLuong = sc.nextInt();
        sc.nextLine();

        System.out.print("Vị trí: ");
        String viTri = sc.nextLine();

        System.out.print("Loại xe (1 = Du lịch, 2 = Chở hàng): ");
        int loai = sc.nextInt();

        Xe xe;

        if (loai == 1) {
            System.out.print("Số chỗ: ");
            int soCho = sc.nextInt();
            xe = new XeDuLich(ma, ten, bienSo, gia, soLuong, viTri, soCho);
        } else {
            System.out.print("Tải trọng (tấn): ");
            double taiTrong = sc.nextDouble();
            xe = new XeChoHang(ma, ten, bienSo, gia, soLuong, viTri, taiTrong);
        }

        them(xe);
    }

    public void timKiemXe() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã xe: ");
        String ma = sc.nextLine();

        Xe xe = timKiem(ma);
        if (xe != null) System.out.println(xe);
        else System.out.println(" Không tìm thấy xe!");
    }

    public void xoaXe() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã xe cần xoá: ");
        String ma = sc.nextLine();
        xoa(ma);
    }

    public void capNhatXe() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã xe cần cập nhật: ");
        String ma = sc.nextLine();

        Xe xeCu = timKiem(ma);
        if (xeCu == null) {
            System.out.println(" Không tìm thấy xe!");
            return;
        }

        System.out.println(" Thông tin hiện tại:");
        System.out.println(xeCu);

        System.out.print("Tên xe mới: ");
        String ten = sc.nextLine();

        System.out.print("Biển số mới: ");
        String bienSo = sc.nextLine();

        System.out.print("Giá thuê mới: ");
        double gia = sc.nextDouble();

        System.out.print("Số lượng mới: ");
        int soLuong = sc.nextInt();
        sc.nextLine();

        System.out.print("Vị trí mới: ");
        String viTri = sc.nextLine();

        Xe xeMoi;

        if (xeCu instanceof XeDuLich) {
            System.out.print("Số chỗ mới: ");
            int soCho = sc.nextInt();
            xeMoi = new XeDuLich(ma, ten, bienSo, gia, soLuong, viTri, soCho);
        } else {
            System.out.print("Tải trọng mới: ");
            double taiTrong = sc.nextDouble();
            xeMoi = new XeChoHang(ma, ten, bienSo, gia, soLuong, viTri, taiTrong);
        }

        capNhat(ma, xeMoi);
    }

    @Override
    public void writeData(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (Xe xe : danhSachXe) {
                String loai = (xe instanceof XeDuLich) ? "DU_LICH" : "CHO_HANG";

                bw.write(xe.getMaXe() + "," +
                        xe.getTenXe() + "," +
                        xe.getBienSo() + "," +
                        xe.getGiaThue() + "," +
                        xe.getSoLuong() + "," +
                        xe.getViTri() + "," +
                        loai + ",");

                if (xe instanceof XeDuLich dl)
                    bw.write(dl.getSoCho() + "");
                else
                    bw.write(((XeChoHang) xe).getTaiTrong() + "");

                bw.newLine();
            }

            System.out.println(" Ghi dữ liệu xe thành công!");
        } catch (IOException e) {
            System.out.println(" Lỗi ghi file: " + e.getMessage());
        }
    }

    @Override
    public void readData(String fileName) {
        danhSachXe.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 8) {
                    String maXe = p[0];
                    String tenXe = p[1];
                    String bienSo = p[2];
                    double giaThue = Double.parseDouble(p[3]);
                    int soLuong = Integer.parseInt(p[4]);
                    String viTri = p[5];
                    String loai = p[6];

                    Xe xe;

                    if (loai.equals("DU_LICH")) {
                        int soCho = Integer.parseInt(p[7]);
                        xe = new XeDuLich(maXe, tenXe, bienSo, giaThue, soLuong, viTri, soCho);
                    } else {
                        double taiTrong = Double.parseDouble(p[7]);
                        xe = new XeChoHang(maXe, tenXe, bienSo, giaThue, soLuong, viTri, taiTrong);
                    }

                    danhSachXe.add(xe);
                }
            }
            System.out.println(" Đọc dữ liệu xe thành công!");
        } catch (IOException e) {
            System.out.println(" Lỗi đọc file: " + e.getMessage());
        }
    }
}
