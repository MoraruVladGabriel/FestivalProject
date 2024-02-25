package repo.repoInMemory;


import model.Casier;
import model.Cumparare;
import model.Spectacol;
import repo.CumparareRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CumparareInMemRepository extends AbstractRepository<Integer, Cumparare> implements CumparareRepository {
    @Override
    public List<Cumparare> filterByCasier(Casier casierCautat) {
        return getAll().stream().filter(x->x.getCasier().equals(casierCautat)).collect(Collectors.toList());

    }

    @Override
    public List<Cumparare> filterBySpectacol(Spectacol spectacolCautat) {
        return getAll().stream().filter(x->x.getSpectacol().equals(spectacolCautat)).collect(Collectors.toList());
    }
}
