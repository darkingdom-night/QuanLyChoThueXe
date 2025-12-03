package QuanLyChoThueXe;

public interface IChucNang<T> {
    void them(T doiTuong);
    void xoa(String ma);
    T timKiem(String ma);
    void hienThi();
    void capNhat(String ma, T doiTuongMoi);
}
