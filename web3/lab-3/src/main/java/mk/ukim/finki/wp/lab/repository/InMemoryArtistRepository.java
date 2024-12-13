package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {

    static List<Artist> artists = new ArrayList<Artist>(5);

    @PostConstruct
    public void init(){
        artists.add(new Artist(1L, "Dua", "Lipa", "Bio1"));
        artists.add(new Artist(2L, "The", "Weeknd", "Bio2"));
        artists.add(new Artist(3L, "Travis", "Scott", "Bio3"));
        artists.add(new Artist(4L, "Drake", "Graham", "Bio4"));
        artists.add(new Artist(5L, "Ariana", "Grande", "Bio5"));
    }
    public List<Artist> findAll(){
        return artists;
    }
    public Optional<Artist> findById(Long id){
        return artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }
    public void deleteArtistById(Long id){
        artists.removeIf(artist -> artist.getId().equals(id));
    }
    public Optional<Artist> addArtist(String firstName,String lastName){
        //novo Id generirano za sekoj nov artist >5
        Long nextId = artists.stream().mapToLong(Artist::getId).max().orElse(0L)+1;
        Artist artist =  new Artist(nextId,firstName, lastName,"");
        artists.removeIf(a->a.getFirstName().equals(firstName));//izbrisi go ako vekje postoi
        artists.add(artist);
        return Optional.of(artist);
    }
}
