package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import tubespbo.MainModel;
import tubespbo.Pegawai;

public class MainHandler implements ActionListener {

    private MainModel model;
    private Main view;

    public MainHandler(Pegawai p) {
        model = new MainModel(p);
        view = new Main();
        view.setVisible(true);
        view.addActionListener(this);
        model.populateTable(view.getTabTransaksi(), Integer.parseInt(view.getCb_month()));
        view.setLocationRelativeTo(null);
    }

    public void resetTambah() {
        view.getEdtNama().setText("");
        view.getEdtBerat().setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getBtnTambah())) {
                if (view.getNama().equals("")) {
                    JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong", "Input Kosong", 0);
                } else {
                    model.tambah(view.getNama(), view.getBerat(), view.getHarga(), view.getDtMasuk(), view.getTabTransaksi(), Integer.parseInt(view.getCb_month()));
                }
                this.resetTambah();
            } else if (source.equals(view.getBtnAmbil())) {
                model.ambil(view.getTabTransaksi(), view.getDtKeluar(), view.getComboStatus(), Integer.parseInt(view.getCb_month()));
            } else if (source.equals(view.getBtnSelesai())) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Yakin transaksi ingin di delete?", "Warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    model.delete(view.getTabTransaksi(), Integer.parseInt(view.getCb_month()));
                }
            } else if (source.equals(view.getCb_monthx())) {
                model.populateTable(view.getTabTransaksi(), Integer.parseInt(view.getCb_month()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
}
