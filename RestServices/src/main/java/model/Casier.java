package model;

import java.util.Objects;

public class Casier extends Entity<Integer> {
    String nume;
    String parola;
    String email;
    String oficiu;

    public Casier(){}

    public Casier(Casier casierNou){
        this.nume = casierNou.getNume();
        this.parola = casierNou.getParola();
        this.email = casierNou.getEmail();
        this.oficiu = casierNou.getOficiu();
    }
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

    @Override
    public String toString() {
        return "Casier{" +
                "nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                ", email='" + email + '\'' +
                ", oficiu='" + oficiu + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casier casier = (Casier) o;
        return Objects.equals(nume, casier.nume) && Objects.equals(parola, casier.parola) && Objects.equals(email, casier.email) && Objects.equals(oficiu, casier.oficiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, parola, email, oficiu);
    }
}
