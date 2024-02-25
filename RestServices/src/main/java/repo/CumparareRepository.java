package repo;



import model.Casier;
import model.Cumparare;
import model.Spectacol;

import java.util.List;

public interface CumparareRepository extends Repository<Integer, Cumparare> {
    List<Cumparare> filterByCasier(Casier casierCautat);
    List<Cumparare> filterBySpectacol(Spectacol spectacolCautat);
}
