package fr.donovan.spotifish.configuration;

import fr.donovan.spotifish.dto.*;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.service.*;
import fr.donovan.spotifish.repository.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
@AllArgsConstructor
@Profile({"prod", "dev"})
public class InitDataLoader implements CommandLineRunner {
    
    private static final Faker faker = new Faker(Locale.FRANCE);

    private static final long NB_USER = 5;
    private static final long NB_ARTIST = 5;
    private static final long NB_MODERATOR = 5;
    private static final long NB_USERLIKEABLEITEM = 5;
    private static final long NB_ALBUM = 5;
    private static final long NB_SONG = 5;
    private static final long NB_PLAYLIST = 5;
    private static final long NB_MUSICALGENRE = 5;
    private static final long NB_HISTORICAL = 5;

    private final UserService userService;
    private final UserRepository userRepository;

    private final LikeableItemService likeableItemService;
    private final LikeableItemRepository likeableItemRepository;

    private final ArtistService artistService;
    private final ArtistRepository artistRepository;

    private final ModeratorService moderatorService;
    private final ModeratorRepository moderatorRepository;

    private final UserLikeableItemService userLikeableItemService;
    private final UserLikeableItemRepository userLikeableItemRepository;

    private final AlbumService albumService;
    private final AlbumRepository albumRepository;

    private final SongAlbumService songAlbumService;
    private final SongAlbumRepository songAlbumRepository;

    private final SongArtistService songArtistService;
    private final SongArtistRepository songArtistRepository;

    private final SongService songService;
    private final SongRepository songRepository;

    private final SongPlaylistService songPlaylistService;
    private final SongPlaylistRepository songPlaylistRepository;

    private final ContributorService contributorService;
    private final ContributorRepository contributorRepository;

    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;

    private final MusicalGenreService musicalGenreService;
    private final MusicalGenreRepository musicalGenreRepository;

    private final HistoricalService historicalService;
    private final HistoricalRepository historicalRepository;

    @Override
    public void run(String... args) {

        createUser();
        createArtist();
        createSong();
        createModerator();
        createAlbum();
        createPlaylist();
        createMusicalGenre();
        System.out.println(" end of init !!!");
    }

    private void createUser() {
        System.out.println("InitDataLoader.createUser");
        long countInsert = NB_USER - userRepository.count();
        for (int i = 0; i < countInsert; i++) {
            System.out.println("i = " + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setName(faker.name().name());
            userDTO.setEmail(faker.internet().emailAddress());
            userDTO.setPassword("12345");
            userDTO.setFirstName(faker.name().firstName());
            userDTO.setLastName(faker.name().lastName());
            userDTO.setBirthAt(faker.timeAndDate().birthday(18, 70));
            System.out.println("userDTO = " + userDTO);
            User user = userService.persist(userDTO);
            System.out.println("user = " + user);
        }
        userRepository.flush();
    }
    private void createArtist() {
        System.out.println("InitDataLoader.createArtist");
        List<String> names = List.of("Alegend", "Aylex", "Dagored", "Pufino", "Zambolino");
        long countInsert = NB_ARTIST - artistRepository.count();
        for (int i = 0; i < countInsert; i++) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setName(names.get(i));
            artistDTO.setEmail(faker.internet().emailAddress());
            artistDTO.setPassword("12345");
            artistDTO.setFirstName(faker.name().firstName());
            artistDTO.setLastName(faker.name().lastName());
            artistDTO.setBirthAt(faker.timeAndDate().birthday(18, 70));
            Artist artist = artistService.persist(artistDTO);
        }
        artistRepository.flush();
    }
    private void createModerator() {
        System.out.println("InitDataLoader.createModerator");
        long countInsert = NB_MODERATOR - moderatorRepository.count();
        for (int i = 0; i < countInsert; i++) {
            ModeratorDTO moderatorDTO = new ModeratorDTO();
            moderatorDTO.setName(faker.name().name());
            moderatorDTO.setEmail(faker.internet().emailAddress());
            moderatorDTO.setPassword("12345");
            moderatorDTO.setFirstName(faker.name().firstName());
            moderatorDTO.setLastName(faker.name().lastName());
            moderatorDTO.setBirthAt(faker.timeAndDate().birthday(18, 70));
            moderatorService.persist(moderatorDTO);
        }
        moderatorRepository.flush();
    }
    private void createUserLikeableItem() {
        System.out.println("InitDataLoader.createUserLikeableItem");
        long countInsert = NB_USERLIKEABLEITEM - userLikeableItemRepository.count();
        for (int i = 0; i < countInsert; i++) {
            userLikeableItemService.persist(likeableItemRepository.findRandom().getSlug(), userRepository.findRandom());
        }
        userLikeableItemRepository.flush();
    }
    private void createAlbum() {
        System.out.println("InitDataLoader.createAlbum");
        long countInsert = NB_ALBUM - albumRepository.count();
        for (int i = 0; i < countInsert; i++) {
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setName(faker.name().name());
            albumDTO.setDescription(faker.kaamelott().quote());
            albumDTO.setCreatedAt(faker.timeAndDate().birthday(1, 10));
            albumDTO.setImage(faker.internet().url());
            albumDTO.setArtistSlug(artistRepository.findRandom().getSlug());
            Album album = albumService.persist(albumDTO);

            createSongAlbum(album);
        }
        albumRepository.flush();
    }
    private void createSong() {
        System.out.println("InitDataLoader.createSong");
        long countInsert = NB_SONG - songRepository.count();
        List<String> names = List.of("Majestic", "Energizer", "Wall Of Sound", "Rock Me Now", "Go On");
        for (int i = 0; i < countInsert; i++) {
            SongDTO songDTO = new SongDTO();
            songDTO.setName(names.get(i));
            songDTO.setCreatedAt(faker.timeAndDate().birthday(1, 10));
            Song song = songService.persist(songDTO);

            createSongArtist(song);
        }
        songRepository.flush();
    }

    private void createPlaylist() {
        System.out.println("InitDataLoader.createPlaylist");
        long countInsert = NB_PLAYLIST - playlistRepository.count();
        for (int i = 0; i < countInsert; i++) {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setName(faker.name().name());
            playlistDTO.setDescription(faker.backToTheFuture().quote());
            playlistDTO.setIsPrivate(false);
            Playlist playlist = playlistService.persist(playlistDTO);

            Contributor contributor = createContributor(playlist);
            createSongPlaylist(contributor);
        }
        playlistRepository.flush();
    }

    private void createMusicalGenre() {
        System.out.println("InitDataLoader.createMusicalGenre");
        long countInsert = NB_MUSICALGENRE - musicalGenreRepository.count();
        for (int i = 0; i < countInsert; i++) {
            MusicalGenreDTO musicalGenreDTO = new MusicalGenreDTO();
            musicalGenreDTO.setName(faker.name().name());
            musicalGenreDTO.setDescription(faker.onePiece().quote());
            musicalGenreDTO.setImage(faker.internet().url());
            musicalGenreService.persist(musicalGenreDTO);
        }
        musicalGenreRepository.flush();
    }
    private void createHistorical() {
        System.out.println("InitDataLoader.createHistorical");
        long countInsert = NB_HISTORICAL - historicalRepository.count();
        for (int i = 0; i < countInsert; i++) {
            HistoricalDTO historicalDTO = new HistoricalDTO();
            historicalDTO.setNumberOflisten(1L);
            historicalDTO.setListenAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
            historicalDTO.setUserSlug(userRepository.findRandom().getSlug());
            historicalDTO.setSongSlug(songRepository.findRandom().getSlug());
            historicalService.persist(historicalDTO);
        }
        historicalRepository.flush();
    }
    private void createSongAlbum(Album album) {
        SongAlbumDTO songAlbumDTO = new SongAlbumDTO();
        songAlbumDTO.setPosition(1);
        songAlbumDTO.setSongSlug(songRepository.findRandom().getSlug());
        songAlbumDTO.setAlbumSlug(album.getSlug());
        songAlbumService.persist(songAlbumDTO);

        songAlbumRepository.flush();
    }
    private void createSongArtist(Song song) {
        SongArtistDTO songArtistDTO = new SongArtistDTO();
        songArtistDTO.setIsPrincipalArtist(true);
        songArtistDTO.setSongSlug(song.getSlug());
        songArtistDTO.setArtistSlug(artistRepository.findRandom().getSlug());
        songArtistService.persist(songArtistDTO);

        songArtistRepository.flush();
    }
    private void createSongPlaylist(Contributor contributor) {
        SongPlaylist songPlaylist = new SongPlaylist();
        songPlaylist.setPlaylist(contributor.getPlaylist());
        songPlaylist.setContributor(contributor);
        songPlaylist.setSong(songRepository.findRandom());
        songPlaylist.setSlug("test");
        songPlaylistRepository.save(songPlaylist);

        songPlaylistRepository.flush();
    }
    private Contributor createContributor(Playlist playlist) {
        ContributorDTO contributorDTO = new ContributorDTO();
        contributorDTO.setIsOwner(true);
        contributorDTO.setUserSlug(userRepository.findRandom().getSlug());
        contributorDTO.setPlaylistSlug(playlist.getSlug());
        Contributor contributor = contributorService.persist(contributorDTO);

        contributorRepository.flush();
        return contributor;
    }
}