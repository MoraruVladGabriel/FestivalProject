package model;

import java.util.Objects;

public class Artist extends Entity<Integer> {
    String nume;
    String tip;
    public Artist(){}

    public Artist(String nume, String tip) {
        this.nume = nume;
        this.tip = tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "nume='" + nume + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(nume, artist.nume) && Objects.equals(tip, artist.tip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, tip);
    }
}
