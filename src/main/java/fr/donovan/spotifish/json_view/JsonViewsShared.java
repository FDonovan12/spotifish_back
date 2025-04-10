package fr.donovan.spotifish.json_view;

public class JsonViewsShared {
    
    public interface AllShared extends ExpireAt, RemainingInvitation, Slug {}
    public interface MinimalShared extends ExpireAt, RemainingInvitation, Slug {}

    public interface AllSharedWithoutId extends ExpireAt, RemainingInvitation, Playlist, Slug {}
    public interface MinimalSharedWithoutId extends ExpireAt, RemainingInvitation, Slug {}

    public interface Uuid  {}
    public interface ExpireAt  {}
    public interface RemainingInvitation  {}
    public interface Playlist  {}
    public interface Slug  {}
}