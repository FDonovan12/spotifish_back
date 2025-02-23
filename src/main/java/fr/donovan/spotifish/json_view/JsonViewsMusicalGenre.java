package fr.donovan.spotifish.json_view;

public class JsonViewsMusicalGenre {
    
    public interface AllMusicalGenre extends Description, Image, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalMusicalGenre extends Description, Image, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllMusicalGenreWithoutId extends Description, Image, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalMusicalGenreWithoutId extends Description, Image, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Description  {}
    public interface Image  {}
}