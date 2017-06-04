package tubespbo;

public class Pegawai extends Person{
    private String username;
    private String password;
    
    public Pegawai(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Pegawai(String nama, String username, String password) {
        super(nama);
        this.username = username;
        this.password = password;
    }

    public String getNama() {
        return super.getNama();
    }

    public void setNama(String nama) {
        super.setNama(nama);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
