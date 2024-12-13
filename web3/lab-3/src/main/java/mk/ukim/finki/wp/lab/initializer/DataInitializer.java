package mk.ukim.finki.wp.lab.initializer;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {
    private  final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    @PostConstruct
    public void init(){

        albumRepository.save(new Album(1L, "Future Nostalgia", "Pop", "2020"));
        albumRepository.save(new Album(2L, "After Hours", "Pop", "2020"));
        albumRepository.save(new Album(3L, "Astroworld", "Rap", "2018"));
        albumRepository.save(new Album(4L, "Scorpion", "Rap", "2018"));
        albumRepository.save(new Album(5L, "Positions", "Pop", "2020"));
        artistRepository.save(new Artist(1L, "Dua", "Lipa", "Bio1"));
        artistRepository.save(new Artist(2L, "The", "Weeknd", "Bio2"));
        artistRepository.save(new Artist(3L, "Travis", "Scott", "Bio3"));
        artistRepository.save(new Artist(4L, "Drake", "Graham", "Bio4"));
        artistRepository.save(new Artist(5L, "Ariana", "Grande", "Bio5"));
        List<Artist> allArtists = artistRepository.findAll();
        List<Album> allAlbums = albumRepository.findAll();
        songRepository.save(new Song(1L, "Blinding Lights", "Pop", 2020, List.of(allArtists.get(0)), allAlbums.get(0)));
        songRepository.save(new Song(2L, "Save Your Tears", "Pop", 2021, List.of(allArtists.get(1)), allAlbums.get(1)));
        songRepository.save(new Song(3L, "Sicko Mode", "Rap", 2018, List.of(allArtists.get(2)), allAlbums.get(2)));
        songRepository.save(new Song(4L, "God's Plan", "Rap", 2018, List.of(allArtists.get(3)), allAlbums.get(3)));
        songRepository.save(new Song(5L, "Thank U, Next", "Pop", 2020, List.of(allArtists.get(4)), allAlbums.get(4)));
    }
}
