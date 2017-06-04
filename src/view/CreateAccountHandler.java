package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import tubespbo.CreateAccountModel;

public class CreateAccountHandler implements ActionListener {

    private CreateAccountModel model;
    private CreateAccount view;

    public CreateAccountHandler() {
        model = new CreateAccountModel();
        view = new CreateAccount();
        view.setVisible(true);
        view.addActionListener(this);
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getBtnCreate())) {
                model.createAccount(view.getNama(), view.getUsername(), view.getPassword());
                JOptionPane.showMessageDialog(view, "Berhasil membuat akun baru!");
                new LoginHandler();
                view.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
}
