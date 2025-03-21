package fr.donovan.spotifish.json_view;

public class JsonViewsContributor {
    
    public interface AllContributor extends IsOwner, StillContributing, User, Playlist, Slug {}
    public interface MinimalContributor extends IsOwner, StillContributing, Slug {}

    public interface AllContributorWithoutId extends IsOwner, StillContributing, User, Playlist, Slug {}
    public interface MinimalContributorWithoutId extends IsOwner, StillContributing, Slug {}
    public interface FromPlaylistContributorWithoutId extends IsOwner, Slug, User {}

    public interface Id  {}
    public interface IsOwner  {}
    public interface StillContributing  {}
    public interface User  {}
    public interface Playlist  {}
    public interface Slug  {}
}