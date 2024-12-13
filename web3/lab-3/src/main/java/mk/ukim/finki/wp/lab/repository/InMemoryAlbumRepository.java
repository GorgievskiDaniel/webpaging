package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlbumRepository {
    static List<Album> albums = new ArrayList<Album>(5);

    @PostConstruct
    public void init(){
        albums.add(new Album(1L, "Future Nostalgia", "Pop", "2020"));
        albums.add(new Album(2L, "After Hours", "Pop", "2020"));
        albums.add(new Album(3L, "Astroworld", "Rap", "2018"));
        albums.add(new Album(4L, "Scorpion", "Rap", "2018"));
        albums.add(new Album(5L, "Positions", "Pop", "2020"));
    }
    public List<Album> findAll(){
        return albums;
    }

    public Album findById(Long id){
        return albums.stream().filter(album -> album.getId().equals(id)).findFirst().orElse(null);
    }
}
