package fr.donovan.spotifish.json_view;

public class JsonViewsUserLikeableItem {
    
    public interface AllUserLikeableItem extends AddAt, User, LikeableItem, Slug {}
    public interface MinimalUserLikeableItem extends AddAt, Slug {}

    public interface AllUserLikeableItemWithoutId extends AddAt, User, LikeableItem, Slug {}
    public interface MinimalUserLikeableItemWithoutId extends AddAt, Slug {}

    public interface Id  {}
    public interface AddAt  {}
    public interface User  {}
    public interface LikeableItem  {}
    public interface Slug  {}
}