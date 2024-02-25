package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Spectacol extends Entity<Integer>{
    String nume;
    Artist artist;
    LocalDateTime data;
    String locatie;
    int locuriDisponibile;
    int locuriVandute;

    public Spectacol(){}

    public Spectacol(Spectacol spectacol){
        this.nume = spectacol.nume;
        this.artist = spectacol.artist;
        this.data = spectacol.data;
        this.locatie = spectacol.locatie;
        this.locuriDisponibile = spectacol.locuriDisponibile;
        this.locuriVandute = spectacol.locuriVandute;
        this.setId(spectacol.getId());
    }

    public Spectacol(String nume, Artist artist, LocalDateTime data, String loc, int locuriDisponibile, int locuriVandute) {
        this.nume = nume;
        this.artist = artist;
        this.data = data;
        this.locatie = loc;
        this.locuriDisponibile = locuriDisponibile;
        this.locuriVandute = locuriVandute;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(int locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

    public int getLocuriVandute() {
        return locuriVandute;
    }

    public void setLocuriVandute(int locuriVandute) {
        this.locuriVandute = locuriVandute;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "nume='" + nume + '\'' +
                ", artist=" + artist +
                ", data=" + data +
                ", locatie='" + locatie + '\'' +
                ", locuriDisponibile=" + locuriDisponibile +
                ", locuriVandute=" + locuriVandute +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spectacol spectacol = (Spectacol) o;
        return locuriDisponibile == spectacol.locuriDisponibile && locuriVandute == spectacol.locuriVandute && Objects.equals(nume, spectacol.nume) && Objects.equals(artist, spectacol.artist) && Objects.equals(data, spectacol.data) && Objects.equals(locatie, spectacol.locatie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, artist, data, locatie, locuriDisponibile, locuriVandute);
    }
}
