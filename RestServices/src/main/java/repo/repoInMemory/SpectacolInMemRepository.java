package repo.repoInMemory;



import model.Spectacol;
import repo.SpectacolRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SpectacolInMemRepository extends AbstractRepository<Integer, Spectacol> implements SpectacolRepository {
    @Override
    public List<Spectacol> filterByDay(LocalDateTime data) {
        return getAll().stream().filter(x->x.getData().equals(data)).collect(Collectors.toList());

    }
}
