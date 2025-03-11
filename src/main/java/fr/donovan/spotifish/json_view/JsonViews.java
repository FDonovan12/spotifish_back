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

public class JsonViews {
    public interface AllJsonViews {}

    public interface UserListJsonViews extends AllJsonViews, MinimalUser, MinimalContributor {}
    public interface UserShowJsonViews extends AllJsonViews, AllUser, MinimalContributor {}

    public interface LikeableItemListJsonViews extends AllJsonViews, MinimalLikeableItem {}
    public interface LikeableItemShowJsonViews extends AllJsonViews, AllLikeableItem {}

    public interface ArtistListJsonViews extends AllJsonViews, MinimalArtist, MinimalAlbum {}
    public interface ArtistShowJsonViews extends AllJsonViews, AllArtist, MinimalAlbum {}

    public interface ModeratorListJsonViews extends AllJsonViews, MinimalModerator {}
    public interface ModeratorShowJsonViews extends AllJsonViews, AllModerator {}

    public interface UserLikeableItemListJsonViews extends AllJsonViews, MinimalUserLikeableItem, MinimalUser, MinimalLikeableItem {}
    public interface UserLikeableItemShowJsonViews extends AllJsonViews, AllUserLikeableItem, MinimalUser, MinimalLikeableItem {}

    public interface AlbumListJsonViews extends AllJsonViews, MinimalAlbum, MinimalArtist {}
    public interface AlbumShowJsonViews extends AllJsonViews, AllAlbum, MinimalArtist {}

    public interface SongAlbumListJsonViews extends AllJsonViews, MinimalSongAlbum, MinimalSong, MinimalAlbum {}
    public interface SongAlbumShowJsonViews extends AllJsonViews, AllSongAlbum, MinimalSong, MinimalAlbum {}

    public interface SongArtistListJsonViews extends AllJsonViews, MinimalSongArtist, MinimalSong, MinimalArtist {}
    public interface SongArtistShowJsonViews extends AllJsonViews, AllSongArtist, MinimalSong, MinimalArtist {}

    public interface SongListJsonViews extends AllJsonViews, FromSongSongPlaylistWithoutId, MinimalMusicalGenre {}
    public interface SongShowJsonViews extends AllJsonViews, FromSongSongPlaylistWithoutId, MinimalMusicalGenre {}

    public interface SongPlaylistListJsonViews extends AllJsonViews, AllSongPlaylist, MinimalSong, MinimalPlaylist, MinimalContributor {}
    public interface SongPlaylistShowJsonViews extends AllJsonViews, AllSongPlaylist, MinimalSong, MinimalPlaylist, MinimalContributor {}

    public interface ContributorListJsonViews extends AllJsonViews, FromContributorSongPlaylistWithoutId, MinimalUser, MinimalPlaylist {}
    public interface ContributorShowJsonViews extends AllJsonViews, FromContributorSongPlaylistWithoutId, MinimalUser, MinimalPlaylist {}

    public interface SharedListJsonViews extends AllJsonViews, MinimalShared, MinimalPlaylist {}
    public interface SharedShowJsonViews extends AllJsonViews, AllShared, MinimalPlaylist {}

    public interface PlaylistListJsonViews extends AllJsonViews, MinimalPlaylist, FromPlaylistSongPlaylistWithoutId, MinimalShared, MinimalContributor, MinimalSong {}
    public interface PlaylistShowJsonViews extends AllJsonViews, MinimalPlaylist, FromPlaylistSongPlaylistWithoutId, MinimalShared, MinimalContributor, MinimalSong {}

    public interface MusicalGenreListJsonViews extends AllJsonViews, MinimalMusicalGenre {}
    public interface MusicalGenreShowJsonViews extends AllJsonViews, AllMusicalGenre {}

    public interface HistoricalListJsonViews extends AllJsonViews, MinimalHistorical, MinimalUser, MinimalSong {}
    public interface HistoricalShowJsonViews extends AllJsonViews, AllHistorical, MinimalUser, MinimalSong {}
}