package fr.donovan.spotifish.json_view;

public class JsonViewsSongAlbum {
    
    public interface AllSongAlbum extends Id, Position, CreatedAt, Song, Album, Slug {}
    public interface MinimalSongAlbum extends Id, Position, CreatedAt, Slug {}

    public interface AllSongAlbumWithoutId extends Position, CreatedAt, Song, Album, Slug {}
    public interface MinimalSongAlbumWithoutId extends Position, CreatedAt, Slug {}

    public interface Id  {}
    public interface Position  {}
    public interface CreatedAt  {}
    public interface Song  {}
    public interface Album  {}
    public interface Slug  {}
}