package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository,AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        List<Artist> artistList = song.getPerformers();
        artistList.add(artist);
        song.setPerformers(artistList);
        songRepository.save(song);
        return artist;
       // return songRepository.addArtistToSong(artist, song);

    }

    @Override
    public Song findByTrackId(Long trackId) {
        return songRepository.findAllByTrackId(trackId).get();
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void addNewSong(String title, String genre, int releaseYear, Album album) {
        Song song = new Song(title, genre, releaseYear, album);
          songRepository.save(song);
    }

    @Override
    public void editSong(Long trackId, String title, String genre, int releaseYear,List<Artist> performers,Album album) {
        //Song song = new Song(trackId,title, genre, releaseYear,performers, album);
        Song song = songRepository.findAllByTrackId(trackId).get();
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setPerformers(performers);
        song.setAlbum(album);
        songRepository.save(song);
    }

    @Override
    public List<Song> searchSongsByAlbum(Long albumId) {
        return songRepository.findAllByAlbumId(albumId);
    }

    @Override
    public Page<Song> findPage(Integer pageNum, Integer pageSize) {


        return this.songRepository.findAll(
                PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "trackId"))
        );

    }
}


