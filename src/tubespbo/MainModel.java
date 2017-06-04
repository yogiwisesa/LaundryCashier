package tubespbo;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainModel {

    Database db;
    Pegawai pegawaiLogin;

    ArrayList<Transaksi> listTransaksi = new ArrayList<>();

    public MainModel(Pegawai p) {
        db = new Database();
        db.connect();
        pegawaiLogin = p;
    }

    public void tambah(String nama, String brt, String hrg, JDateChooser dtMasuk, JTable tabTransaksi, int month) {
        int berat = Integer.parseInt(brt);
        int harga = Integer.parseInt(hrg);
        db.connect();
        Cucian c = new Cucian(berat);
        db.saveCucian(c);
        Pelanggan p = new Pelanggan(nama, c);
        db.savePelanggan(p);
        java.util.Date jud = dtMasuk.getDate();
        Transaksi transaksi = new Transaksi(pegawaiLogin, p, jud, jud, harga * berat, "Belum Lunas"); // status baru buat pasti belum lunas
        System.out.println(transaksi.getStatus());
        db.saveTransaksi(transaksi);
        populateTable(tabTransaksi, month);
    }

    public void populateTable(JTable tabTransaksi, int month) {
        db.connect();
        listTransaksi.clear();
        System.out.println(month);
        if (month == 0) {
            listTransaksi.addAll(db.loadTransaksi());
        } else {
            listTransaksi.addAll(db.loadTransaksiMonth(month));
        }

        DefaultTableModel model = (DefaultTableModel) tabTransaksi.getModel();
        model.setRowCount(0);
        if (listTransaksi.size() > 0) {
            for (int i = 0; i < listTransaksi.size(); i++) {
                String tgl;
                if (listTransaksi.get(i).getTglKeluar().toString().equals("0001-01-01")) {
                    tgl = "Belum diambil";
                } else {
                    tgl = listTransaksi.get(i).getTglKeluar().toString();
                }
                Object[] row = {listTransaksi.get(i).getKodeTransaksi(),
                    listTransaksi.get(i).getPelanggan().getNama(),
                    listTransaksi.get(i).getPegawai().getNama(),
                    listTransaksi.get(i).getTglMasuk(),
                    tgl,
                    listTransaksi.get(i).getTotalHarga(),
                    listTransaksi.get(i).getStatus()};
                model.addRow(row);
            }
        }
    }

    public void ambil(JTable tabTransaksi, JDateChooser dtKeluar, JComboBox combo_status, int month) {
        System.out.println(tabTransaksi.getSelectedRow());
        if (tabTransaksi.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Pilih transaksi pada tabel terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            db.ambilLaundry(tabTransaksi.getValueAt(tabTransaksi.getSelectedRow(), 0).toString(), dtKeluar.getDate(), combo_status.getSelectedItem().toString());
            populateTable(tabTransaksi, month);
        }
    }

    public void delete(JTable tabTransaksi, int month) {
        if (tabTransaksi.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Pilih transaksi pada tabel terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            db.deleteLaundry(tabTransaksi.getValueAt(tabTransaksi.getSelectedRow(), 0).toString());
            populateTable(tabTransaksi, month);
        }
    }

}
