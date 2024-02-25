package repo;



import model.Artist;

import java.util.List;

public interface ArtistRepository extends Repository<Integer, Artist>{
    List<Artist> filterByTip(String tip);

}
