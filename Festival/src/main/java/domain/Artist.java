package domain;

public class Artist extends Entity<Long>{
    String nume;
    public Artist(){}

    public Artist(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

}
