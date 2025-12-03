package QuanLyChoThueXe;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        QuanLyXeImpl qlXe = new QuanLyXeImpl();
        QuanLyHopDongImpl qlHD = new QuanLyHopDongImpl();

        XeDuLich xe1 = new XeDuLich("X01", "Toyota Vios", "43A-12345", 800000, 5, "Kệ A1", 5);
        XeChoHang xe2 = new XeChoHang("X02", "Isuzu 8T", "43C-67890", 1200000, 3, "Kệ B2", 8.0);
        XeDuLich xe3 = new XeDuLich("X03", "Honda City", "43B-11223", 750000, 4, "Kệ A2", 4);
        XeChoHang xe4 = new XeChoHang("X04", "Hyundai 10T", "43D-33445", 1500000, 2, "Kệ B3", 10.0);

        qlXe.them(xe1);
        qlXe.them(xe2);
        qlXe.them(xe3);
        qlXe.them(xe4);

        KhachHang kh1 = new KhachHang("KH01", "Nguyen Van A", "123456789", "0987654321");
        KhachHang kh2 = new KhachHang("KH02", "Tran Thi B", "987654321", "0123456789");
        KhachHang kh3 = new KhachHang("KH03", "Le Van C", "567891234", "0912345678");
        HopDongThue hd1 = new HopDongThue("HD01", kh1, xe1, 3); 
        HopDongThue hd2 = new HopDongThue("HD02", kh2, xe2, 5); 
        HopDongThue hd3 = new HopDongThue("HD03", kh3, xe3, 2); 

        qlHD.them(hd1);
        qlHD.them(hd2);
        qlHD.them(hd3);

        int chon;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm xe mới");
            System.out.println("2. Hiển thị danh sách xe");
            System.out.println("3. Tìm kiếm xe");
            System.out.println("4. Xoá xe");
            System.out.println("5. Cập nhật xe");
            System.out.println("6. Thêm hợp đồng mới");
            System.out.println("7. Hiển thị danh sách hợp đồng");
            System.out.println("8. Tìm kiếm hợp đồng");
            System.out.println("9. Xoá hợp đồng");
            System.out.println("10. Cập nhật hợp đồng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            while (!sc.hasNextInt()) { 
                System.out.println("Vui lòng nhập số nguyên!"); 
                sc.nextLine(); 
                System.out.print("Chọn: "); 
            }
            chon = sc.nextInt(); 
            sc.nextLine();

            switch (chon) {
                case 1 -> qlXe.themXeMoi();
                case 2 -> qlXe.hienThi();
                case 3 -> qlXe.timKiemXe();
                case 4 -> qlXe.xoaXe();
                case 5 -> qlXe.capNhatXe();
                case 6 -> qlHD.themHopDongMoi(qlXe);
                case 7 -> qlHD.hienThi();
                case 8 -> qlHD.timKiemHopDong();
                case 9 -> qlHD.xoaHopDong();
                case 10 -> qlHD.capNhatHopDong();
                case 0 -> System.out.println("Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);

        sc.close();
    }
}
