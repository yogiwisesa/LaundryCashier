package tubespbo;

public class Pelanggan extends Person{
    private int idPelanggan;
    private Cucian cucian;

    public Pelanggan(String nama) {
        super(nama);
    }
    
    public Pelanggan(String nama, Cucian cucian) {
        super(nama);
        this.cucian = cucian;
    }
    
    public Pelanggan(int idPelanggan, String nama, Cucian cucian) {
        super(nama);
        this.idPelanggan = idPelanggan;
        this.cucian = cucian;
    }
    
    public void setId(int id){
        idPelanggan = id;
    }
    
    public int getId(){
        return idPelanggan;
    }
    
    public void setNama(String nama){
       super.setNama(nama);
    }
    
    public String getNama(){
        return super.getNama();
    }
        
    public void createCucian(int berat){
        this.cucian = new Cucian(idPelanggan, berat);
    }
    
    public int getBeratCucian(){
        return this.cucian.getBerat();
    }

    public Cucian getCucian() {
        return cucian;
    }

    public void setCucian(Cucian cucian) {
        this.cucian = cucian;
    }
}
