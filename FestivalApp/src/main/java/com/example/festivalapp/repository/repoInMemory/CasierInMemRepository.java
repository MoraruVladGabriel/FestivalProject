package com.example.festivalapp.repository.repoInMemory;

import com.example.festivalapp.domain.Casier;
import com.example.festivalapp.repository.CasierRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CasierInMemRepository extends AbstractRepository<Integer, Casier> implements CasierRepository {
    @Override
    public List<Casier> filterByOficiu(String oficiuCautat) {
        return getAll().stream().filter(x->x.getOficiu().toLowerCase().equals(oficiuCautat.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Casier getCasierByEmail(String emailCautat) {
        for (Casier casier:this.getAll()) {
            if(casier.getEmail().equals(emailCautat)){
                return casier;
            }
        }
        return null;
    }
}
