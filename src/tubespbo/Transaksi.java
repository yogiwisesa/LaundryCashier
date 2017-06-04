package tubespbo;

import java.util.Date;

public class Transaksi {
    private int kodeTransaksi;
    private Pegawai pegawai;
    private Pelanggan pelanggan;
    private java.util.Date tglMasuk;
    private java.util.Date tglKeluar;
    private long bayar;
    private String status;

    public Transaksi(Pegawai pegawai, Pelanggan pelanggan, Date tglMasuk, Date tglKeluar, long bayar, String status) {
        this.pegawai = pegawai;
        this.pelanggan = pelanggan;
        this.tglMasuk = tglMasuk;
        this.tglKeluar = tglKeluar;
        this.bayar = bayar;
        this.status = status;
    }
    
    public Transaksi(int kodeTransaksi, Pegawai pegawai, Pelanggan pelanggan, java.util.Date tglMasuk, java.util.Date tglKeluar, long bayar,String status) {
        this.kodeTransaksi = kodeTransaksi;
        this.pegawai = pegawai;
        this.pelanggan = pelanggan;
        this.tglMasuk = tglMasuk;
        this.tglKeluar = tglKeluar;
        this.bayar = bayar;
        this.status = status;
    }
    
    public Transaksi(Pelanggan pelanggan, java.util.Date tglKeluar) {
        this.pelanggan = pelanggan;
        this.tglKeluar = tglKeluar;
    }

    public int getKodeTransaksi() {
        return kodeTransaksi;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public java.util.Date getTglMasuk() {
        return tglMasuk;
    }

    public java.util.Date getTglKeluar() {
        return tglKeluar;
    }

    public long getBayar() {
        return bayar;
    }

    public void setBayar(long bayar) {
        this.bayar = bayar;
    }

    public void setKodeTransaksi(int kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public void setTglMasuk(java.util.Date tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public void setTglKeluar(java.util.Date tglKeluar) {
        this.tglKeluar = tglKeluar;
    }
    
    public long getTotalHarga(){
        return bayar;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
