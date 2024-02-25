package model;

import java.util.Objects;

public class Cumparare extends Entity<Integer> {
    String cumparator;
    int locuri;
    Spectacol spectacol;
    Casier casier;

    public Cumparare(){}

    public Cumparare(String cumparator, int locuri, Spectacol spectacol, Casier casier) {
        this.cumparator = cumparator;
        this.locuri = locuri;
        this.spectacol = spectacol;
        this.casier = casier;
    }

    public String getCumparator() {
        return cumparator;
    }

    public void setCumparator(String cumparator) {
        this.cumparator = cumparator;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

    public Casier getCasier() {
        return casier;
    }

    public void setCasier(Casier casier) {
        this.casier = casier;
    }

    @Override
    public String toString() {
        return "Cumparare{" +
                "cumparator='" + cumparator + '\'' +
                ", locuri=" + locuri +
                ", spectacol=" + spectacol +
                ", casier=" + casier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cumparare cumparare = (Cumparare) o;
        return locuri == cumparare.locuri && Objects.equals(cumparator, cumparare.cumparator) && Objects.equals(spectacol, cumparare.spectacol) && Objects.equals(casier, cumparare.casier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cumparator, locuri, spectacol, casier);
    }
}
