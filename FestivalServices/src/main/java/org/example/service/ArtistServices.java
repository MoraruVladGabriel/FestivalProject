package org.example.service;

import com.example.festivalapp.domain.Artist;
import com.example.festivalapp.repository.ArtistRepository;
import com.example.festivalapp.repository.RepositoryException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ArtistServices {
    private ArtistRepository artistRepo;

    public ArtistServices(ArtistRepository artistRepo) {
        this.artistRepo = artistRepo;
    }

    public void addArtist(String nume, String tip) throws ServicesException {
        try {
            Artist artist = new Artist(nume, tip);
            artistRepo.save(artist);
        } catch (RepositoryException e) {
            throw new ServicesException("Error adding form " + e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteArtist(Integer id) throws ServicesException {
        try {
            artistRepo.delete(artistRepo.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new ServicesException("Error deleting form " + e);
        }
    }

    public void updateArtist(Integer id, String numeNou, String tipNou) throws ServicesException {
        try {
            artistRepo.update(id, new Artist(numeNou, tipNou));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new ServicesException("Error updating form " + e);
        }
    }

    public List<Artist> filterByTip(String tipCautat) {
        return artistRepo.filterByTip(tipCautat);
    }

    public Collection<Artist> getAll() {
        return artistRepo.getAll();
    }
}
