package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import tubespbo.LoginModel;

public class LoginHandler implements ActionListener {

    private LoginModel model;
    private Login view;

    public LoginHandler() {
        model = new LoginModel();
        view = new Login();
        view.setVisible(true);
        view.addActionListener(this);
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getBtnCreate())) {
               new CreateAccountHandler();
               view.dispose();
            } else if(source.equals(view.getBtnLogin())){
                if (model.login(view.getUsername(),view.getPassword())){
                    view.dispose();
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
}
