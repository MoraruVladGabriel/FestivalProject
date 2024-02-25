package domain;

import java.time.LocalDateTime;

public class Spectacol extends Entity<Long>{
    String nume;
    Artist artist;
    LocalDateTime data;
    String locatie;
    int locuriDisponibile;
    int locuriVandute;
    public Spectacol(){}

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

    public Artist getIdArtist() {
        return artist;
    }

    public void setIdArtist(Artist artist) {
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
}
