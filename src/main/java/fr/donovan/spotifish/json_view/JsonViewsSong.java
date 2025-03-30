package fr.donovan.spotifish.json_view;

public class JsonViewsSong {
    
    public interface AllSong extends Path, Duration, Image, CreatedAt, NumberOfListen, SongArtists, SongAlbums, SongPlaylists, MusicalGenres, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalSong extends Path, Duration, Image, CreatedAt, NumberOfListen, SongArtists, MusicalGenres, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface SearchSong extends Path, Duration, Image, CreatedAt, NumberOfListen, SongArtists, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Path  {}
    public interface Duration  {}
    public interface Image  {}
    public interface CreatedAt  {}
    public interface NumberOfListen  {}
    public interface SongArtists  {}
    public interface SongAlbums  {}
    public interface SongPlaylists  {}
    public interface MusicalGenres  {}
}