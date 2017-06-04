package tubespbo;

public class Date {
    private int tanggal;
    private int bulan;
    private int tahun;

    public Date(int hari, int bulan, int tahun) {
        this.tanggal = hari;
        this.bulan = bulan;
        this.tahun = tahun;
    }

    public int getHari() {
        return tanggal;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setHari(int hari) {
        this.tanggal = hari;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    /*
    public Date hitungHari(Date tglMasuk, int jumHari){ // Untuk menghitung tgl keluar berdasarkan input jumlah hari
        Date tglKeluar;
        tglKeluar = new Date(tglMasuk.getHari(), tglMasuk.getBulan(), tglMasuk.getTahun());
        tglKeluar.setHari(tglMasuk.getHari() + jumHari);
        if (tglKeluar.getHari() > 30) { //asumsi tgl setiap bulan hanya 30
            tglKeluar.setHari(tglKeluar.getHari() - 30);
            tglKeluar.setBulan(tglKeluar.getBulan() +1);

            if (tglKeluar.getBulan() > 12){
                tglKeluar.setBulan(tglKeluar.getBulan() - 12);
                tglKeluar.setTahun(tglKeluar.getTahun() + 1);
            }
        }

        if (tglKeluar.getBulan() > 12){
            tglKeluar.setBulan(tglKeluar.getBulan() - 12);
            tglKeluar.setTahun(tglKeluar.getTahun() + 1);
        }

        return tglKeluar;
    }
    */
}
