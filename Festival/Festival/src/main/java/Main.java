import domain.Artist;
import domain.Casier;
import domain.Cumparare;
import domain.Spectacol;
import repo.ArtistRepository;
import repo.CasierRepository;
import repo.CumparareRepository;
import repo.SpectacolRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        try{
            props.load(new FileReader("bd.config"));
        }catch (IOException e){
            System.out.println("Cannot find bd.config " + e);
        }
        ArtistRepository artistRepo = new ArtistRepository(props);
        Artist artist = new Artist("Marian Popovici","comediant");
        artistRepo.save(artist);
        System.out.println("Toti artistii din DB: ");
        for(Artist artistF:artistRepo.findAll()){
            System.out.println(artistF.toString());
        }
        Integer id = 2;
        System.out.println(artistRepo.findById(id));

        //artistRepo.delete(artistRepo.findById(1));

        System.out.println("Toti artistii din DB: ");
        for(Artist artistF:artistRepo.findAll()){
            System.out.println(artistF.toString());
        }

        CasierRepository casierRepo = new CasierRepository(props);
        casierRepo.save(new Casier("Andrei","parola","email","A"));
        casierRepo.save(new Casier("Marius","parola","email","B"));
        System.out.println("Toti casierii din BD: ");
        for(Casier casier: casierRepo.findAll()){
            System.out.println(casier.toString());
        }
        System.out.println(casierRepo.findById(1));
//        casierRepo.delete(casierRepo.findById(1));
//        System.out.println("Toti casierii din BD: ");
//        for(Casier casier: casierRepo.findAll()){
//            System.out.println(casier.toString());
//        }

        SpectacolRepository spectacolRepo = new SpectacolRepository(props,artistRepo);
        Artist art = new Artist("Florin Calinescu","actor");
        art.setId(2);
        spectacolRepo.save(new Spectacol("Spectacol1",art, LocalDateTime.of(2023, 3, 13, 19, 30, 0),"locatie1",200,78));
        spectacolRepo.save(new Spectacol("Spectacol2",art, LocalDateTime.of(2023, 3, 21, 16, 0, 0),"locatie2",250,176));
        spectacolRepo.save(new Spectacol("Spectacol3",art, LocalDateTime.of(2023, 5, 23, 12, 30, 0),"locatie3",145,57));
        System.out.println("Toate spectacolele din BD: ");
        for (Spectacol spectacol: spectacolRepo.findAll()){
            System.out.println(spectacol.toString());
        }

        System.out.println(spectacolRepo.findById(1));

        CumparareRepository cumparareRepo = new CumparareRepository(props, casierRepo, spectacolRepo);

        cumparareRepo.save(new Cumparare("Marius Mari",3,spectacolRepo.findById(1),casierRepo.findById(3)));
        cumparareRepo.save(new Cumparare("Vlad  Mic",2,spectacolRepo.findById(1),casierRepo.findById(3)));
        System.out.println("Toate tranzactiile din BD: ");
        for (Cumparare cumparare:cumparareRepo.findAll()){
            System.out.println(cumparare.toString());
        }

        System.out.println(cumparareRepo.findById(1));
    }
}