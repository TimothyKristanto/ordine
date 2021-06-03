package Model;

public class OrderList {
    private String nama, imagePath;
    private int harga, jumlah;

    public OrderList(String nama, String imagePath, int harga, int jumlah) {
        this.nama = nama;
        this.imagePath = imagePath;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public OrderList() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
