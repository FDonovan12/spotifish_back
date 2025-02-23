package fr.donovan.spotifish.json_view;

public class JsonViewsArtist {
    
    public interface AllArtist extends SongArtists, Albums, Members, Group, JsonViewsUser.AllUser {}
    public interface MinimalArtist extends JsonViewsUser.MinimalUser {}

    public interface AllArtistWithoutId extends SongArtists, Albums, Members, Group, JsonViewsUser.AllUserWithoutId {}
    public interface MinimalArtistWithoutId extends JsonViewsUser.MinimalUserWithoutId {}

    public interface SongArtists  {}
    public interface Albums  {}
    public interface Members  {}
    public interface Group  {}
}