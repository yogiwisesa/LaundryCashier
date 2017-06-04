package tubespbo;

public class CreateAccountModel {
    Database d;

    public CreateAccountModel() {
        d = new Database();
        d.connect();
    }
    
    public void createAccount(String nama, String username, String password){
        Pegawai p = new Pegawai(nama, username,password);
        d.savePegawai(p);
    }
}
