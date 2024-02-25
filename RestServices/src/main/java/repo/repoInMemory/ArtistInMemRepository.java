package repo.repoInMemory;



import model.Artist;
import org.springframework.stereotype.Component;
import repo.ArtistRepository;

import java.util.List;
import java.util.stream.Collectors;


public class ArtistInMemRepository extends AbstractRepository<Integer, Artist> implements ArtistRepository {
    @Override
    public List<Artist> filterByTip(String tip) {
        return getAll().stream().filter(x->x.getTip().toLowerCase().equals(tip.toLowerCase())).collect(Collectors.toList());
    }

}
