package fr.donovan.spotifish.json_view;

public class JsonViewsLikeableItem {
    
    public interface AllLikeableItem extends Uuid, Name, Slug {}
    public interface MinimalLikeableItem extends Uuid, Name, Slug {}

    public interface AllLikeableItemWithoutId extends Name, Slug {}
    public interface MinimalLikeableItemWithoutId extends Name, Slug {}

    public interface Uuid  {}
    public interface Name  {}
    public interface Slug  {}
}

