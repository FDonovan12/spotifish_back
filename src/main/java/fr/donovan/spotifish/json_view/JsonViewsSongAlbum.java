package fr.donovan.spotifish.json_view;

public class JsonViewsSongAlbum {
    
    public interface AllSongAlbum extends Position, Song, Album, Slug {}
    public interface MinimalSongAlbum extends Position, Slug {}

    public interface AllSongAlbumWithoutId extends Position, Song, Album, Slug {}
    public interface MinimalSongAlbumWithoutId extends Position, Slug {}
    public interface FromSongSongAlbumWithoutId extends Position, Album, Slug {}
    public interface FromAlbumSongAlbumWithoutId extends Position, Song, Slug {}

    public interface Id  {}
    public interface Position  {}
    public interface Song  {}
    public interface Album  {}
    public interface Slug  {}
}