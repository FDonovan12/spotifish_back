//package fr.donovan.spotifish.configuration;
//
//import fr.donovan.spotifish.dto.*;
//import fr.donovan.spotifish.entity.*;
//import fr.donovan.spotifish.service.*;
//import fr.donovan.spotifish.repository.*;
//import lombok.AllArgsConstructor;
//import net.datafaker.Faker;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.Locale;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@AllArgsConstructor
//public class InitDataLoader implements CommandLineRunner {
//
//    private static final Faker faker = new Faker(Locale.FRANCE);
//
//    private static final long NB_USER = 5;
//    private static final long NB_LIKEABLEITEM = 5;
//    private static final long NB_ARTIST = 5;
//    private static final long NB_MODERATOR = 5;
//    private static final long NB_USERLIKEABLEITEM = 5;
//    private static final long NB_ALBUM = 5;
//    private static final long NB_SONGALBUM = 5;
//    private static final long NB_SONGARTIST = 5;
//    private static final long NB_SONG = 5;
//    private static final long NB_SONGPLAYLIST = 5;
//    private static final long NB_CONTRIBUTOR = 5;
//    private static final long NB_SHARED = 5;
//    private static final long NB_PLAYLIST = 5;
//    private static final long NB_MUSICALGENRE = 5;
//    private static final long NB_HISTORICAL = 5;
//
//    private final UserService userService;
//    private final UserRepository userRepository;
//
//    private final LikeableItemService likeableItemService;
//    private final LikeableItemRepository likeableItemRepository;
//
//    private final ArtistService artistService;
//    private final ArtistRepository artistRepository;
//
//    private final ModeratorService moderatorService;
//    private final ModeratorRepository moderatorRepository;
//
//    private final UserLikeableItemService userLikeableItemService;
//    private final UserLikeableItemRepository userLikeableItemRepository;
//
//    private final AlbumService albumService;
//    private final AlbumRepository albumRepository;
//
//    private final SongAlbumService songAlbumService;
//    private final SongAlbumRepository songAlbumRepository;
//
//    private final SongArtistService songArtistService;
//    private final SongArtistRepository songArtistRepository;
//
//    private final SongService songService;
//    private final SongRepository songRepository;
//
//    private final SongPlaylistService songPlaylistService;
//    private final SongPlaylistRepository songPlaylistRepository;
//
//    private final ContributorService contributorService;
//    private final ContributorRepository contributorRepository;
//
//    private final SharedService sharedService;
//    private final SharedRepository sharedRepository;
//
//    private final PlaylistService playlistService;
//    private final PlaylistRepository playlistRepository;
//
//    private final MusicalGenreService musicalGenreService;
//    private final MusicalGenreRepository musicalGenreRepository;
//
//    private final HistoricalService historicalService;
//    private final HistoricalRepository historicalRepository;
//
//    @Override
//    public void run(String... args) {
//
//        createUser();
//        createLikeableItem();
//        createArtist();
//        createModerator();
//        createUserLikeableItem();
//        createAlbum();
//        createSongAlbum();
//        createSongArtist();
//        createSong();
//        createSongPlaylist();
//        createContributor();
//        createShared();
//        createPlaylist();
//        createMusicalGenre();
//        createHistorical();
//    System.out.println(" end of init !!!");
//    }
//
//    private void createUser() {
//        long countInsert = NB_USER - userRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setEmail(faker.internet().emailAddress());
//            userDTO.setPassword(None);
//            userDTO.setUsername(None);
//            userDTO.setFirstName(None);
//            userDTO.setLastName(None);
//            userDTO.setBirthAt(faker.timeAndDate().birthday(18, 70));
//            userDTO.setCreatedAt(LocalDateTime.from(faker.timeAndDate().past(10 * 365, TimeUnit.DAYS)));
//            userDTO.setActivationCode(None);
//            userDTO.setActivationCodeExpireAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
//            userService.persist(userDTO);
//        }
//        userRepository.flush();
//    }
//    private void createLikeableItem() {
//        long countInsert = NB_LIKEABLEITEM - likeableItemRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            LikeableItemDTO likeableItemDTO = new LikeableItemDTO();
//            likeableItemDTO.setName(None);
//            likeableItemService.persist(likeableItemDTO);
//        }
//        likeableItemRepository.flush();
//    }
//    private void createArtist() {
//        long countInsert = NB_ARTIST - artistRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            ArtistDTO artistDTO = new ArtistDTO();
//            artistService.persist(artistDTO);
//        }
//        artistRepository.flush();
//    }
//    private void createModerator() {
//        long countInsert = NB_MODERATOR - moderatorRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            ModeratorDTO moderatorDTO = new ModeratorDTO();
//            moderatorService.persist(moderatorDTO);
//        }
//        moderatorRepository.flush();
//    }
//    private void createUserLikeableItem() {
//        long countInsert = NB_USERLIKEABLEITEM - userLikeableItemRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            UserLikeableItemDTO userLikeableItemDTO = new UserLikeableItemDTO();
//            userLikeableItemDTO.setAddAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
//            userLikeableItemDTO.setUserId(userRepository.findRandom().getUuid());
//            userLikeableItemDTO.setLikeableItemId(likeableItemRepository.findRandom().getUuid());
//            userLikeableItemService.persist(userLikeableItemDTO);
//        }
//        userLikeableItemRepository.flush();
//    }
//    private void createAlbum() {
//        long countInsert = NB_ALBUM - albumRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            AlbumDTO albumDTO = new AlbumDTO();
//            albumDTO.setDescription(None);
//            albumDTO.setImage(faker.internet().url());
//            albumDTO.setCreatedAt(LocalDateTime.from(faker.timeAndDate().past(10 * 365, TimeUnit.DAYS)));
//            albumDTO.setArtistId(artistRepository.findRandom().getUuid());
//            albumService.persist(albumDTO);
//        }
//        albumRepository.flush();
//    }
//    private void createSongAlbum() {
//        long countInsert = NB_SONGALBUM - songAlbumRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            SongAlbumDTO songAlbumDTO = new SongAlbumDTO();
//            songAlbumDTO.setPosition(None);
//            songAlbumDTO.setCreatedAt(LocalDateTime.from(faker.timeAndDate().past(10 * 365, TimeUnit.DAYS)));
//            songAlbumDTO.setSongId(songRepository.findRandom().getUuid());
//            songAlbumDTO.setAlbumId(albumRepository.findRandom().getUuid());
//            songAlbumService.persist(songAlbumDTO);
//        }
//        songAlbumRepository.flush();
//    }
//    private void createSongArtist() {
//        long countInsert = NB_SONGARTIST - songArtistRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            SongArtistDTO songArtistDTO = new SongArtistDTO();
//            songArtistDTO.setIsPrincipalArtist(None);
//            songArtistDTO.setSongId(songRepository.findRandom().getUuid());
//            songArtistDTO.setArtistId(artistRepository.findRandom().getUuid());
//            songArtistService.persist(songArtistDTO);
//        }
//        songArtistRepository.flush();
//    }
//    private void createSong() {
//        long countInsert = NB_SONG - songRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            SongDTO songDTO = new SongDTO();
//            songDTO.setPath(faker.internet().url());
//            songDTO.setDuration(None);
//            songDTO.setImage(faker.internet().url());
//            songDTO.setCreatedAt(LocalDateTime.from(faker.timeAndDate().past(10 * 365, TimeUnit.DAYS)));
//            songDTO.setNumberOfListen(None);
//            songService.persist(songDTO);
//        }
//        songRepository.flush();
//    }
//    private void createSongPlaylist() {
//        long countInsert = NB_SONGPLAYLIST - songPlaylistRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            SongPlaylistDTO songPlaylistDTO = new SongPlaylistDTO();
//            songPlaylistDTO.setPosition(None);
//            songPlaylistDTO.setCreatedAt(LocalDateTime.from(faker.timeAndDate().past(10 * 365, TimeUnit.DAYS)));
//            songPlaylistDTO.setSongId(songRepository.findRandom().getUuid());
//            songPlaylistDTO.setPlaylistId(playlistRepository.findRandom().getUuid());
//            songPlaylistDTO.setContributorId(contributorRepository.findRandom().getId());
//            songPlaylistService.persist(songPlaylistDTO);
//        }
//        songPlaylistRepository.flush();
//    }
//    private void createContributor() {
//        long countInsert = NB_CONTRIBUTOR - contributorRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            ContributorDTO contributorDTO = new ContributorDTO();
//            contributorDTO.setIsOwner(None);
//            contributorDTO.setStillContributing(None);
//            contributorDTO.setUserId(userRepository.findRandom().getUuid());
//            contributorDTO.setPlaylistId(playlistRepository.findRandom().getUuid());
//            contributorService.persist(contributorDTO);
//        }
//        contributorRepository.flush();
//    }
//    private void createShared() {
//        long countInsert = NB_SHARED - sharedRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            SharedDTO sharedDTO = new SharedDTO();
//            sharedDTO.setExpireAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
//            sharedDTO.setRemainingInvitation(None);
//            sharedDTO.setUserId(playlistRepository.findRandom().getUuid());
//            sharedService.persist(sharedDTO);
//        }
//        sharedRepository.flush();
//    }
//    private void createPlaylist() {
//        long countInsert = NB_PLAYLIST - playlistRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            PlaylistDTO playlistDTO = new PlaylistDTO();
//            playlistDTO.setDescription(None);
//            playlistDTO.setImage(faker.internet().url());
//            playlistDTO.setCeratedAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
//            playlistDTO.setIsPrivate(None);
//            playlistDTO.setSharedId(sharedRepository.findRandom().getUuid());
//            playlistService.persist(playlistDTO);
//        }
//        playlistRepository.flush();
//    }
//    private void createMusicalGenre() {
//        long countInsert = NB_MUSICALGENRE - musicalGenreRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            MusicalGenreDTO musicalGenreDTO = new MusicalGenreDTO();
//            musicalGenreDTO.setDescription(None);
//            musicalGenreDTO.setImage(faker.internet().url());
//            musicalGenreService.persist(musicalGenreDTO);
//        }
//        musicalGenreRepository.flush();
//    }
//    private void createHistorical() {
//        long countInsert = NB_HISTORICAL - historicalRepository.count();
//        for (int i = 0; i < countInsert; i++) {
//            HistoricalDTO historicalDTO = new HistoricalDTO();
//            historicalDTO.setNumberOflisten(None);
//            historicalDTO.setListenAt(faker.timeAndDate().birthday(-2, 10).atStartOfDay());
//            historicalDTO.setUserId(userRepository.findRandom().getUuid());
//            historicalDTO.setSongId(songRepository.findRandom().getUuid());
//            historicalService.persist(historicalDTO);
//        }
//        historicalRepository.flush();
//    }
//}