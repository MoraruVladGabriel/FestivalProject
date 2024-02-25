package com.example.festivalapp.repository.repoInMemory;

import com.example.festivalapp.domain.Artist;
import com.example.festivalapp.repository.ArtistRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;
@Component
public class ArtistInMemRepository extends AbstractRepository<Integer, Artist> implements ArtistRepository {
    @Override
    public List<Artist> filterByTip(String tip) {
        return getAll().stream().filter(x->x.getTip().toLowerCase().equals(tip.toLowerCase())).collect(Collectors.toList());
    }

}
