package Model;

public class FoodMenu {
    private String nama, imagePath, genre;
    private int harga;

    public FoodMenu(String nama, String imagePath, int harga, String genre) {
        this.nama = nama;
        this.imagePath = imagePath;
        this.harga = harga;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public FoodMenu() {
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
}
