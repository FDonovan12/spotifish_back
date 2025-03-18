package fr.donovan.spotifish.json_view;

public class JsonViewsSongPlaylist {
    
    public interface AllSongPlaylist extends Position, CreatedAt, Song, Playlist, Contributor, Slug {}
    public interface MinimalSongPlaylist extends Position, CreatedAt, Slug {}

    public interface AllSongPlaylistWithoutId extends Position, CreatedAt, Song, Playlist, Contributor, Slug {}
    public interface FromSongSongPlaylistWithoutId extends Position, CreatedAt, Playlist, Contributor, Slug {}
    public interface FromPlaylistSongPlaylistWithoutId extends Position, CreatedAt, Song, Contributor, Slug {}
    public interface FromContributorSongPlaylistWithoutId extends Position, CreatedAt, Song, Playlist, Slug {}

    public interface Id  {}
    public interface Position  {}
    public interface CreatedAt  {}
    public interface Song  {}
    public interface Playlist  {}
    public interface Contributor  {}
    public interface Slug  {}
}