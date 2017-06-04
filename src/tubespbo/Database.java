package tubespbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    private String server = "jdbc:mysql://localhost:3306/tubeslaundry";
    private Statement statement = null;
    private Statement statementPelanggan = null;
    private Statement statementCucian = null;
    private Statement statementTransaksi = null;
    private Connection connection = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(server, "root", "");
            statement = connection.createStatement();
            statementPelanggan = connection.createStatement();
            statementCucian = connection.createStatement();
            statementTransaksi = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat koneksi");
        }
    }

    public void dc() {
        try {
            connection = DriverManager.getConnection(server, "root", "");
            connection.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat diconnect");
        }
    }

    public void savePegawai(Pegawai p) {
        try {
            String query = "insert into pegawai(username,password,nama) values"
                    + "('" + p.getUsername() + "', "
                    + "'" + p.getPassword() + "',"
                    + "'" + p.getNama()
                    + "')";
            statement.execute(query);
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat save pegawai");
        }
    }

    public Boolean login(Pegawai p) {
        try {
            String query = "select * from pegawai where username='" + p.getUsername() + "' and password = '" + p.getPassword() + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat login");
        }
    }

    public Pegawai getAccount(String username) {
        try {
            String query = "select * from pegawai Where username ='" + username + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Pegawai p = new Pegawai(rs.getString(3), rs.getString(2), rs.getString(1));
                return p;
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat load peserta");
        }
    }

    public void saveCucian(Cucian c) {
        try {
            String query = "insert into cucian(berat) values"
                    + "('" + c.getBerat() + "')";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                c.setId(generatedId);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat save cucian");
        }
    }

    public void savePelanggan(Pelanggan p) {
        try {
            String query = "insert into pelanggan(nama,id_cucian) values"
                    + "('" + p.getNama() + "' , '" + p.getCucian().getId() + "')";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                p.setId(generatedId);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat save cucian");
        }
    }

    public void saveTransaksi(Transaksi t) {
        try {

            long date = t.getTglMasuk().getTime();
            java.sql.Date sqlDate = new java.sql.Date(date);

            String query = "insert into transaksi(tglMasuk,tglKeluar,totalHarga,username,id_pelanggan,status) values"
                    + "('" + sqlDate + "' , '"
                    + "1-1-1" + "','"
                    + t.getTotalHarga() + "','"
                    + t.getPegawai().getUsername() + "','"
                    + t.getPelanggan().getId() + "','"
                    + t.getStatus() + "')";

            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                t.setKodeTransaksi(generatedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat save transaksi");
        }
    }

    public Cucian getCucian(int id_cucian) {
        try {
            String query = "select * from cucian Where id_cucian ='" + id_cucian + "'";
            ResultSet rs = statementCucian.executeQuery(query);
            if (rs.next()) {
                Cucian c = new Cucian(rs.getInt("id_cucian"), rs.getInt("berat"));

                return c;
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat load peserta");
        }
    }

    public int getIdPelanggan(String nama) {
        try {
            String query = "Select id_pelanggan from pelanggan where nama ='" + nama + "'";
            ResultSet rs = statementTransaksi.executeQuery(query);
            return rs.getInt("id_pelanggan");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat get Id Transaksi");
        }
    }

    public void updateTransaksi(Transaksi t) {
        try {
            long date = t.getTglKeluar().getTime();
            java.sql.Date sqlDate = new java.sql.Date(date);
            String nama = t.getPelanggan().getNama();
            int idPel = getIdPelanggan("id_pelanggan");
            String query = "update transaksi set tglKeluar = '" + sqlDate + "' where id_pelanggan = '" + idPel + "'";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat update transaksi");
        }
    }

    public Pelanggan getPelanggan(int id_pelanggan) {
        try {
            String query = "select * from pelanggan where id_pelanggan='" + id_pelanggan + "'";
            ResultSet rs = statementPelanggan.executeQuery(query);
            if (rs.next()) {
                Pelanggan p = new Pelanggan(rs.getInt(2), rs.getString("nama"), this.getCucian(rs.getInt(3)));
                return p;
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("terjadi kesalahan saat load pelanggan");
        }
    }

    public ArrayList<Transaksi> loadTransaksi() {
        try {
            ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
            System.out.println(daftarTransaksi.size());
            String query = "select * from transaksi";
            ResultSet rs = statementTransaksi.executeQuery(query);
            while (rs.next()) {
                Transaksi t = new Transaksi(rs.getInt(4), this.getAccount(rs.getString(5)), this.getPelanggan(rs.getInt(6)), rs.getDate(1), rs.getDate(2), rs.getLong(3), rs.getString(7));
                daftarTransaksi.add(t);
            }
            System.out.println(daftarTransaksi.size());
            return daftarTransaksi;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat load transaksi");
        }
    }
    
        public ArrayList<Transaksi> loadTransaksiMonth(int month) {
        try {
            ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
            System.out.println(daftarTransaksi.size());
            String query = "select * from transaksi where MONTH(tglMasuk)=" + month;
            ResultSet rs = statementTransaksi.executeQuery(query);
            while (rs.next()) {
                Transaksi t = new Transaksi(rs.getInt(4), this.getAccount(rs.getString(5)), this.getPelanggan(rs.getInt(6)), rs.getDate(1), rs.getDate(2), rs.getLong(3), rs.getString(7));
                daftarTransaksi.add(t);
            }
            System.out.println(daftarTransaksi.size());
            return daftarTransaksi;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat load transaksi");
        }
    }

    public void ambilLaundry(String kodeTransaksi, java.util.Date tglKeluar, String status) {
        try {
            long date = tglKeluar.getTime();
            java.sql.Date sqlDate = new java.sql.Date(date);
            String query = "update transaksi set tglKeluar = '" + sqlDate + "',status='" + status + "' where id_transaksi = '" + kodeTransaksi + "'";
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat update transaksi");
        }
    }

    public void deleteLaundry(String idTransaksi) {
        try {
            String query = "delete from transaksi where id_transaksi ='" + idTransaksi +"'";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat dekete transaksi");
        }
    }

}
