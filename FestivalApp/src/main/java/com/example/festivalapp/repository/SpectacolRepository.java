package com.example.festivalapp.repository;

import com.example.festivalapp.domain.Spectacol;

import java.time.LocalDateTime;
import java.util.List;

public interface SpectacolRepository extends Repository<Integer, Spectacol>{
    List<Spectacol> filterByDay(LocalDateTime data);
    //void updateLocuri(Integer id, int locuriDisponibileNoi, int locuriVanduteNoi);
}
