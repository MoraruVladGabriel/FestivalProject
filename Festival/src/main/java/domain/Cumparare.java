package domain;

public class Cumparare extends Entity<Long>{
    String cumparator;
    int locuri;
    Spectacol spectacol;
    Casier casier;

    public Cumparare(){}

    public Cumparare(String cumparator, int locuri,Spectacol spectacol,Casier casier) {
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
}
