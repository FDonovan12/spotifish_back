package fr.donovan.spotifish.json_view;

public class JsonViewsSongArtist {
    
    public interface AllSongArtist extends Id, IsPrincipalArtist, Song, Artist, Slug {}
    public interface MinimalSongArtist extends Id, IsPrincipalArtist, Song, Artist, Slug {}

    public interface AllSongArtistWithoutId extends IsPrincipalArtist, Song, Artist, Slug {}
    public interface MinimalSongArtistWithoutId extends IsPrincipalArtist, Slug {}
    public interface FromSongSongArtistWithoutId extends IsPrincipalArtist, Slug, Artist {}
    public interface FromArtistSongArtistWithoutId extends IsPrincipalArtist, Slug, Song {}

    public interface Id  {}
    public interface IsPrincipalArtist  {}
    public interface Song  {}
    public interface Artist  {}
    public interface Slug  {}
}