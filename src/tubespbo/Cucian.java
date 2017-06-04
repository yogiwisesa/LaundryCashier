package tubespbo;

public class Cucian {
    private int idCucian;
    private int berat;
    
    public Cucian(int berat) {
        this.berat = berat;
    }    
        
    public Cucian(int id, int berat){
        idCucian = id;
        this.berat = berat;
    }

    public void setId(int id){
        idCucian = id;
    }
    
    public int getId(){
        return idCucian;
    }
    
    public void setBerat(int berat){
        this.berat = berat;
    }
    
    public int getBerat(){
        return berat;
    }
}
