package assignments.assignment2;

public class User {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String name;
    private String phoneNumber;
    private String email;
    private String location;
    private String role;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        // TODO: buat constructor untuk class ini
        this. name = nama;
        this.phoneNumber = nomorTelepon;
        this.email = email;
        this.location = lokasi;
        this.role = role;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getName() {
        return this.name;
    }

    public void setName(String nama) {
        this.name = nama;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getLocation () {
        return this.location;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    public String getRole () {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
