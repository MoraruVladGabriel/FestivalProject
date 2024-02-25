package service;



import model.Casier;
import repo.CasierRepository;
import repo.RepositoryException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CasierServices {
    private CasierRepository casierRepo;

    public CasierServices(CasierRepository casierRepo) {
        this.casierRepo = casierRepo;
    }

    public void addCasier(String nume, String parola, String email, String oficiu) throws ServicesException {
        try {
            casierRepo.save(new Casier(nume, parola, email, oficiu));
        } catch (RepositoryException e) {
            throw new ServicesException("Error adding form " + e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCasier(Integer id) throws ServicesException {
        try {
            casierRepo.delete(casierRepo.findById(id));
        } catch (RepositoryException e) {
            throw new ServicesException("Error deleting form " + e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCasie(Integer id, String numeNou, String parolaNoua, String emailNou, String oficiuNou) throws ServicesException {
        try {
            casierRepo.update(id, new Casier(numeNou, parolaNoua, emailNou, oficiuNou));
        } catch (RepositoryException e) {
            throw new ServicesException("Error updating form " + e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Casier> getAll() {
        return casierRepo.getAll();
    }

    public List<Casier> filterByOficiu(String oficiuCautat) {
        return casierRepo.filterByOficiu(oficiuCautat);
    }

    public Casier getCasierByEmail(String emailCautat){
        return casierRepo.getCasierByEmail(emailCautat);
    }
}
