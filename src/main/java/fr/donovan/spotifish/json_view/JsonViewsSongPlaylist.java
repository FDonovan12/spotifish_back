package fr.donovan.spotifish.json_view;

public class JsonViewsSongPlaylist {
    
    public interface AllSongPlaylist extends Id, Position, CreatedAt, Song, Playlist, Contributor, Slug {}
    public interface MinimalSongPlaylist extends Id, Position, CreatedAt, Slug {}

    public interface AllSongPlaylistWithoutId extends Position, CreatedAt, Song, Playlist, Contributor, Slug {}
    public interface MinimalSongPlaylistWithoutId extends Position, CreatedAt, Slug {}

    public interface Id  {}
    public interface Position  {}
    public interface CreatedAt  {}
    public interface Song  {}
    public interface Playlist  {}
    public interface Contributor  {}
    public interface Slug  {}
}