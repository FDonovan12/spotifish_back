package fr.donovan.spotifish.json_view;

public class JsonViewsUser {
    
    public interface AllUser extends Uuid, Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, ActivationCodeExpireAt, UserLikeableItems, Contributors, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalUser extends Uuid, Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, ActivationCodeExpireAt, JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllUserWithoutId extends Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, ActivationCodeExpireAt, UserLikeableItems, Contributors, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalUserWithoutId extends Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, ActivationCodeExpireAt, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Uuid  {}
    public interface Email  {}
    public interface Password  {}
    public interface Username  {}
    public interface FirstName  {}
    public interface LastName  {}
    public interface BirthAt  {}
    public interface CreatedAt  {}
    public interface ActivationCode  {}
    public interface ActivationCodeExpireAt  {}
    public interface UserLikeableItems  {}
    public interface Contributors  {}
}