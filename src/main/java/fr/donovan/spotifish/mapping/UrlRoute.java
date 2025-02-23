package fr.donovan.spotifish.mapping;

public class UrlRoute implements UserUrlRoute, LikeableItemUrlRoute, ArtistUrlRoute, ModeratorUrlRoute, UserLikeableItemUrlRoute, AlbumUrlRoute, SongAlbumUrlRoute, SongArtistUrlRoute, SongUrlRoute, SongPlaylistUrlRoute, ContributorUrlRoute, SharedUrlRoute, PlaylistUrlRoute, MusicalGenreUrlRoute, HistoricalUrlRoute{

    public static final String URL_API = "/api";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_REGISTER = "/register";
    public static final String URL_ADMIN = "/admin";

}