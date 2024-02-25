package repo;



import model.Casier;

import java.util.List;

public interface CasierRepository extends Repository<Integer, Casier> {
    List<Casier> filterByOficiu(String oficiuCautat);
    Casier getCasierByEmail(String emailCautat);
}
