package tubespbo;

import javax.swing.JOptionPane;
import view.MainHandler;

public class LoginModel {
    Database db;
    
    public LoginModel() {
        db = new Database();
        db.connect();
    }
    
    public boolean login (String username, String password){
        Pegawai p = new Pegawai(username, password);
        Database d = new Database();
        d.connect();
        System.out.println(username + " " +  password);
        if (db.login(p)) {
            p = d.getAccount(username);
            new MainHandler(p);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
