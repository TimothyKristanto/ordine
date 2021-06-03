package Model;

import java.util.Date;

public class OrderHistory {
    private String nama, harga, imageurl, tanggal;

    public OrderHistory(String nama, String harga, String imageurl, String tanggal) {
        this.nama = nama;
        this.harga = harga;
        this.imageurl = imageurl;
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
