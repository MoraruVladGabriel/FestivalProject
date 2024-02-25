package domain;

public class Casier extends Entity<Long>{
    String nume;
    String parola;
    String email;
    String oficiu;

    public Casier(){}
    public Casier(String nume, String parola, String email, String oficiu) {
        this.nume = nume;
        this.parola = parola;
        this.email = email;
        this.oficiu = oficiu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOficiu() {
        return oficiu;
    }

    public void setOficiu(String oficiu) {
        this.oficiu = oficiu;
    }
}
