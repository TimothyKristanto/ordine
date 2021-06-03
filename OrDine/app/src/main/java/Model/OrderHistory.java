package Model;

public class OrderHistory {
    private String nama, harga, imageurl;

    public OrderHistory(String nama, String harga, String imageurl) {
        this.nama = nama;
        this.harga = harga;
        this.imageurl = imageurl;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public OrderHistory(){

    }
}
