package fr.donovan.spotifish.json_view;

public class JsonViewsShared {
    
    public interface AllShared extends Uuid, ExpireAt, RemainingInvitation, User, Slug {}
    public interface MinimalShared extends Uuid, ExpireAt, RemainingInvitation, Slug {}

    public interface AllSharedWithoutId extends ExpireAt, RemainingInvitation, User, Slug {}
    public interface MinimalSharedWithoutId extends ExpireAt, RemainingInvitation, Slug {}

    public interface Uuid  {}
    public interface ExpireAt  {}
    public interface RemainingInvitation  {}
    public interface User  {}
    public interface Slug  {}
}