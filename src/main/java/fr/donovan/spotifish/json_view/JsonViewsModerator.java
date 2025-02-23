package fr.donovan.spotifish.json_view;

public class JsonViewsModerator {
    
    public interface AllModerator extends JsonViewsArtist.AllArtist {}
    public interface MinimalModerator extends JsonViewsArtist.MinimalArtist {}

    public interface AllModeratorWithoutId extends JsonViewsArtist.AllArtistWithoutId {}
    public interface MinimalModeratorWithoutId extends JsonViewsArtist.MinimalArtistWithoutId {}

}