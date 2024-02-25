package com.example.festivalapp.repository;

import com.example.festivalapp.domain.Casier;
import com.example.festivalapp.domain.Cumparare;
import com.example.festivalapp.domain.Spectacol;

import java.util.List;

public interface CumparareRepository extends Repository<Integer, Cumparare>{
    List<Cumparare> filterByCasier(Casier casierCautat);
    List<Cumparare> filterBySpectacol(Spectacol spectacolCautat);
}
