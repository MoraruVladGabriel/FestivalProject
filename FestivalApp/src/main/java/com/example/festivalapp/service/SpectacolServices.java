package com.example.festivalapp.service;

import com.example.festivalapp.domain.Artist;
import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.repository.ArtistRepository;
import com.example.festivalapp.repository.RepositoryException;
import com.example.festivalapp.repository.SpectacolRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpectacolServices {
    private SpectacolRepository spectacolRepo;
    private ArtistRepository artistRepo;

    public SpectacolServices(SpectacolRepository spectacolRepo, ArtistRepository artistRepo) {
        this.spectacolRepo = spectacolRepo;
        this.artistRepo = artistRepo;
    }

    public void addSpectacol(String nume,Integer idArtist,LocalDateTime data,String locatie,int locuriDisponibile,int locuriVandute) throws ServicesException {
        try {
            spectacolRepo.save(new Spectacol(nume,artistRepo.findById(idArtist),data,locatie,locuriDisponibile,locuriVandute));
        } catch (RepositoryException e) {
            throw new ServicesException("Error adding form " + e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSpectacol(Integer id) throws ServicesException {
        try {
            spectacolRepo.delete(spectacolRepo.findById(id));
        } catch (RepositoryException e) {
            throw new ServicesException("Error deleting form " + e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSpectacol(Integer id, String numeNou, Integer idArtisNou, LocalDateTime dataNoua, String locatieNoua, int locuriDisponibileNoi, int locuriVanduteNoi) throws ServicesException {
        try {
            spectacolRepo.update(id,new Spectacol(numeNou,artistRepo.findById(idArtisNou),dataNoua,locatieNoua,locuriDisponibileNoi,locuriVanduteNoi));
        } catch (RepositoryException e) {
            throw new ServicesException("Error adding form " + e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Spectacol> getAll(){
        return spectacolRepo.getAll();
    }

    public Iterable<Spectacol> findAll() throws SQLException {return spectacolRepo.findAll();}

    public List<Spectacol> filterByDay(LocalDate data) throws SQLException {
        List<Spectacol> list = new ArrayList<>();
        for(Spectacol spectacol:spectacolRepo.findAll()){
            if(spectacol.getData().toLocalDate().equals(data)){
                list.add(spectacol);
            }
        }
        return list;
    }

    public void cumparaBilete(Integer id, Integer numarLocuri){
        try {
            Spectacol spectacolNou = new Spectacol();
            spectacolNou.setId(id);
            spectacolNou.setNume(spectacolRepo.findById(id).getNume());
            spectacolNou.setArtist(spectacolRepo.findById(id).getArtist());
            spectacolNou.setData(spectacolRepo.findById(id).getData());
            spectacolNou.setLocatie(spectacolRepo.findById(id).getLocatie());
            spectacolNou.setLocuriDisponibile(spectacolRepo.findById(id).getLocuriDisponibile() - numarLocuri);
            spectacolNou.setLocuriVandute(spectacolRepo.findById(id).getLocuriVandute() + numarLocuri);
            spectacolRepo.update(id,spectacolNou);
            //updateSpectacol(id);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Spectacol findById(Integer id){
        try {
            return this.spectacolRepo.findById(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
