package fr.donovan.spotifish.json_view;

public class JsonViewsSongArtist {
    
    public interface AllSongArtist extends IsPrincipalArtist, Song, Artist, Slug {}

    public interface FromSongSongArtistWithoutId extends IsPrincipalArtist, Slug, Artist {}
    public interface FromArtistSongArtistWithoutId extends IsPrincipalArtist, Slug, Song {}

    public interface Id  {}
    public interface IsPrincipalArtist  {}
    public interface Song  {}
    public interface Artist  {}
    public interface Slug  {}
}



