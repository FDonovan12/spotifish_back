package fr.donovan.spotifish.json_view;
import fr.donovan.spotifish.json_view.JsonViewsUser.*;
import fr.donovan.spotifish.json_view.JsonViewsLikeableItem.*;
import fr.donovan.spotifish.json_view.JsonViewsArtist.*;
import fr.donovan.spotifish.json_view.JsonViewsModerator.*;
import fr.donovan.spotifish.json_view.JsonViewsUserLikeableItem.*;
import fr.donovan.spotifish.json_view.JsonViewsAlbum.*;
import fr.donovan.spotifish.json_view.JsonViewsSongAlbum.*;
import fr.donovan.spotifish.json_view.JsonViewsSongArtist.*;
import fr.donovan.spotifish.json_view.JsonViewsSong.*;
import fr.donovan.spotifish.json_view.JsonViewsSongPlaylist.*;
import fr.donovan.spotifish.json_view.JsonViewsContributor.*;
import fr.donovan.spotifish.json_view.JsonViewsShared.*;
import fr.donovan.spotifish.json_view.JsonViewsPlaylist.*;
import fr.donovan.spotifish.json_view.JsonViewsMusicalGenre.*;
import fr.donovan.spotifish.json_view.JsonViewsHistorical.*;
import org.checkerframework.checker.units.qual.min;

public class JsonViews {
    public interface AllJsonViews {}

    public interface UserListJsonViews extends AllJsonViews, MinimalUser, MinimalContributor {}
    public interface UserShowJsonViews extends AllJsonViews, AllUser, MinimalContributor {}

    public interface LikeableItemListJsonViews extends AllJsonViews, MinimalLikeableItem {}
    public interface LikeableItemShowJsonViews extends AllJsonViews, AllLikeableItem {}
    public interface LikeableItemSearchJsonViews extends
            AllJsonViews,
            SearchSong,
            FromSongSongArtistWithoutId,
            SearchPlaylistWithoutId,
            SearchAlbumWithoutId,
            FromPlaylistContributorWithoutId {}

    public interface ArtistListJsonViews extends AllJsonViews, MinimalArtist, MinimalAlbum {}
    public interface ArtistShowJsonViews extends AllJsonViews, AllArtist, MinimalAlbum, FromArtistSongArtistWithoutId, FromArtistMinimalSong {}

    public interface ModeratorListJsonViews extends AllJsonViews, MinimalModerator {}
    public interface ModeratorShowJsonViews extends AllJsonViews, AllModerator {}

    public interface UserLikeableItemListJsonViews extends AllJsonViews, MinimalUserLikeableItem, MinimalUser, MinimalLikeableItem {}
    public interface UserLikeableItemShowJsonViews extends AllJsonViews, AllUserLikeableItem, MinimalUser, MinimalLikeableItem {}

    public interface AlbumListJsonViews extends AllJsonViews, MinimalAlbum, MinimalArtist {}
    public interface AlbumShowJsonViews extends AllJsonViews, AllAlbum, MinimalArtist, FromAlbumSongAlbumWithoutId, MinimalSong {}

    public interface SongAlbumListJsonViews extends AllJsonViews, MinimalSongAlbum, MinimalSong, MinimalAlbum {}
    public interface SongAlbumShowJsonViews extends AllJsonViews, AllSongAlbum, MinimalSong, MinimalAlbum {}

    public interface SongArtistListJsonViews extends AllJsonViews, AllSongArtist, MinimalSong, MinimalArtist {}
    public interface SongArtistShowJsonViews extends AllJsonViews, AllSongArtist, MinimalSong, MinimalArtist {}

    public interface SongListJsonViews extends AllJsonViews, MinimalSong, FromSongSongPlaylistWithoutId, FromSongSongArtistWithoutId, MinimalArtist, MinimalMusicalGenre {}
    public interface SongShowJsonViews extends AllJsonViews, AllSong, FromSongSongPlaylistWithoutId, FromSongSongArtistWithoutId, MinimalArtist, MinimalMusicalGenre {}

    public interface SongPlaylistListJsonViews extends AllJsonViews, AllSongPlaylist, MinimalSong, MinimalPlaylist, MinimalContributor {}
    public interface SongPlaylistShowJsonViews extends AllJsonViews, AllSongPlaylist, MinimalSong, MinimalPlaylist, MinimalContributor {}

    public interface ContributorListJsonViews extends AllJsonViews, FromContributorSongPlaylistWithoutId, MinimalUser, MinimalPlaylist {}
    public interface ContributorShowJsonViews extends AllJsonViews, FromContributorSongPlaylistWithoutId, MinimalUser, MinimalPlaylist {}

    public interface SharedListJsonViews extends AllJsonViews, MinimalShared, MinimalPlaylist {}
    public interface SharedShowJsonViews extends AllJsonViews, AllShared {}

    public interface PlaylistListJsonViews extends AllJsonViews, MinimalPlaylist, FromPlaylistSongPlaylistWithoutId, MinimalContributor, MinimalSong {}
    public interface PlaylistShowJsonViews extends
            AllJsonViews,
            AllPlaylist,
            FromPlaylistSongPlaylistWithoutId,
            FromSongSongArtistWithoutId,
            FromPlaylistContributorWithoutId,
            MinimalUser,
            MinimalSong,
            MinimalShared {}

    public interface MusicalGenreListJsonViews extends AllJsonViews, MinimalMusicalGenre {}
    public interface MusicalGenreShowJsonViews extends AllJsonViews, AllMusicalGenre {}

    public interface HistoricalListJsonViews extends AllJsonViews, MinimalHistorical, MinimalUser, MinimalSong {}
    public interface HistoricalShowJsonViews extends AllJsonViews, AllHistorical, MinimalUser, MinimalSong {}
}