package Model;

public class User {
    private String nama, email, tableNumber;

    public User(String nama, String email, String tableNumber) {
        this.nama = nama;
        this.email = email;
        this.tableNumber = tableNumber;
    }

    public User() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
}
