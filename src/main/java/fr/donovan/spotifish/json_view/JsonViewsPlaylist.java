package fr.donovan.spotifish.json_view;

public class JsonViewsPlaylist {
    
    public interface AllPlaylist extends Description, Image, CreatedAt, IsPrivate, Contributors, SongPlaylists, Shared, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalPlaylist extends Description, Image, CreatedAt, IsPrivate, Contributors, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllPlaylistWithoutId extends Description, Image, CreatedAt, IsPrivate, Contributors, SongPlaylists, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalPlaylistWithoutId extends Description, Image, CreatedAt, IsPrivate, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}
    public interface SearchPlaylistWithoutId extends Image, CreatedAt, Contributors, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Description  {}
    public interface Image  {}
    public interface CreatedAt  {}
    public interface IsPrivate  {}
    public interface Shared  {}
    public interface Contributors  {}
    public interface SongPlaylists  {}
    public interface User {}
}