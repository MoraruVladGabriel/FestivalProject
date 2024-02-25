package com.example.festivalapp.repository;

import com.example.festivalapp.domain.Artist;

import java.util.List;

public interface ArtistRepository extends Repository<Integer, Artist>{
    List<Artist> filterByTip(String tip);
}
