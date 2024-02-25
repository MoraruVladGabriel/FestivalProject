package com.example.festivalapp.repository.repoInMemory;

import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.repository.SpectacolRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SpectacolInMemRepository extends AbstractRepository<Integer, Spectacol> implements SpectacolRepository {
    @Override
    public List<Spectacol> filterByDay(LocalDateTime data) {
        return getAll().stream().filter(x->x.getData().equals(data)).collect(Collectors.toList());

    }
}
