package QuanLyChoThueXe;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyHopDongImpl implements IChucNang<HopDongThue>, IReadWrite {
    private ArrayList<HopDongThue> danhSachHopDong = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void them(HopDongThue hd) {
        if (timKiem(hd.getMaHD()) != null) {
            System.out.println("Mã HĐ đã tồn tại!");
        } else {
            danhSachHopDong.add(hd);
            System.out.println("Đã thêm HĐ " + hd.getMaHD());
        }
    }

    @Override
    public void xoa(String maHD) {
        HopDongThue hd = timKiem(maHD);
        if (hd != null) {
            danhSachHopDong.remove(hd);
            System.out.println("Đã xoá HĐ " + maHD);
        } else {
            System.out.println("Không tìm thấy HĐ!");
        }
    }

    @Override
    public HopDongThue timKiem(String maHD) {
        for (HopDongThue hd : danhSachHopDong)
            if (hd.getMaHD().equalsIgnoreCase(maHD))
                return hd;
        return null;
    }

    @Override
    public void hienThi() {
        if (danhSachHopDong.isEmpty())
            System.out.println("Chưa có hợp đồng nào.");
        else
            danhSachHopDong.forEach(System.out::println);
    }

    @Override
    public void capNhat(String maHD, HopDongThue hdMoi) {
        for (int i = 0; i < danhSachHopDong.size(); i++) {
            if (danhSachHopDong.get(i).getMaHD().equalsIgnoreCase(maHD)) {
                danhSachHopDong.set(i, hdMoi);
                System.out.println("Cập nhật HĐ " + maHD + " thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy HĐ " + maHD + " để cập nhật!");
    }

    public void capNhatHopDong() {
        System.out.print("Nhập mã HĐ cần cập nhật: ");
        String maHD = sc.nextLine();
        HopDongThue hd = timKiem(maHD);
        if (hd == null) {
            System.out.println("Không tìm thấy HĐ!");
            return;
        }
        System.out.println("Thông tin hiện tại: " + hd);
        System.out.print("Nhập số ngày thuê mới: ");
        int soNgay = sc.nextInt(); sc.nextLine();
        HopDongThue hdMoi = new HopDongThue(hd.getMaHD(), hd.getKh(), hd.getXe(), soNgay);
        capNhat(maHD, hdMoi);
    }

    public void timKiemHopDong() {
        System.out.print("Nhập mã HĐ cần tìm: ");
        String maHD = sc.nextLine();
        HopDongThue hd = timKiem(maHD);
        if (hd != null) System.out.println(hd);
        else System.out.println("Không tìm thấy HĐ!");
    }

    public void xoaHopDong() {
        System.out.print("Nhập mã HĐ cần xoá: ");
        String maHD = sc.nextLine();
        xoa(maHD);
    }

    public void themHopDongMoi(QuanLyXeImpl qlXe) {
        System.out.print("Nhập mã HĐ: ");
        String maHD = sc.nextLine();
        if (timKiem(maHD) != null) {
            System.out.println("Mã HĐ đã tồn tại!");
            return;
        }

        System.out.print("Nhập mã xe muốn thuê: ");
        String maXe = sc.nextLine();
        Xe xe = qlXe.timKiem(maXe);
        if (xe == null) {
            System.out.println("Xe không tồn tại!");
            return;
        }

        System.out.print("Số ngày thuê: ");
        int soNgay = sc.nextInt(); sc.nextLine();
        if (soNgay <= 0) {
            System.out.println("Số ngày thuê phải >0");
            return;
        }

        KhachHang kh = new KhachHang();
        kh.nhap();
        HopDongThue hd = new HopDongThue(maHD, kh, xe, soNgay);
        them(hd);
    }

    @Override
    public void readData(String fileName) {
        danhSachHopDong.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 13) {
                    String maHD = p[0];
                    KhachHang kh = new KhachHang(p[1], p[2], p[3], p[4]);

                    String maXe = p[5];
                    String tenXe = p[6];
                    String bienSo = p[7];
                    double gia = Double.parseDouble(p[8]);
                    int soLuong = Integer.parseInt(p[9]);
                    String viTri = p[10];
                    String loaiXe = p[11];
                    Xe xe;
                    if (loaiXe.equalsIgnoreCase("DU_LICH")) {
                        int soCho = Integer.parseInt(p[12]);
                        xe = new XeDuLich(maXe, tenXe, bienSo, gia, soLuong, viTri, soCho);
                    } else {
                        double taiTrong = Double.parseDouble(p[12]);
                        xe = new XeChoHang(maXe, tenXe, bienSo, gia, soLuong, viTri, taiTrong);
                    }

                    int soNgay = Integer.parseInt(p[13]);
                    HopDongThue hd = new HopDongThue(maHD, kh, xe, soNgay);
                    danhSachHopDong.add(hd);
                }
            }
            System.out.println("Đọc dữ liệu hợp đồng thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file HĐ: " + e.getMessage());
        }
    }

    @Override
    public void writeData(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (HopDongThue hd : danhSachHopDong) {
                KhachHang kh = hd.getKh();
                Xe xe = hd.getXe();
                String loaiXe = (xe instanceof XeDuLich) ? "DU_LICH" : "CHO_HANG";
                String thuocTinh = (xe instanceof XeDuLich) ? String.valueOf(((XeDuLich) xe).getSoCho())
                                                              : String.valueOf(((XeChoHang) xe).getTaiTrong());

                bw.write(hd.getMaHD() + "," +
                         kh.getMaKH() + "," + kh.getTenKH() + "," + kh.getCccd() + "," + kh.getSdt() + "," +
                         xe.getMaXe() + "," + xe.getTenXe() + "," + xe.getBienSo() + "," + xe.getGiaThue() + "," +
                         xe.getSoLuong() + "," + xe.getViTri() + "," + loaiXe + "," + thuocTinh + "," +
                         hd.getSoNgay());
                bw.newLine();
            }
            System.out.println("Ghi dữ liệu hợp đồng thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file HĐ: " + e.getMessage());
        }
    }
}
