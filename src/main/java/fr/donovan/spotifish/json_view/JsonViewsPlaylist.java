package fr.donovan.spotifish.json_view;

public class JsonViewsPlaylist {
    
    public interface AllPlaylist extends Description, Image, CeratedAt, IsPrivate, Shared, Contributors, SongPlaylists, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalPlaylist extends Description, Image, CeratedAt, IsPrivate, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllPlaylistWithoutId extends Description, Image, CeratedAt, IsPrivate, Shared, Contributors, SongPlaylists, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalPlaylistWithoutId extends Description, Image, CeratedAt, IsPrivate, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Description  {}
    public interface Image  {}
    public interface CeratedAt  {}
    public interface IsPrivate  {}
    public interface Shared  {}
    public interface Contributors  {}
    public interface SongPlaylists  {}
}