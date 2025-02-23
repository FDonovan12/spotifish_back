package fr.donovan.spotifish.json_view;

public class JsonViewsAlbum {
    
    public interface AllAlbum extends Description, Image, CreatedAt, Artist, SongAlbums, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalAlbum extends Description, Image, CreatedAt, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllAlbumWithoutId extends Description, Image, CreatedAt, Artist, SongAlbums, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalAlbumWithoutId extends Description, Image, CreatedAt, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Description  {}
    public interface Image  {}
    public interface CreatedAt  {}
    public interface Artist  {}
    public interface SongAlbums  {}
}