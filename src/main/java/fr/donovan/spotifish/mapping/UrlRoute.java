package fr.donovan.spotifish.mapping;

public class UrlRoute implements UserUrlRoute, LikeableItemUrlRoute, ArtistUrlRoute, ModeratorUrlRoute, UserLikeableItemUrlRoute, AlbumUrlRoute, SongAlbumUrlRoute, SongArtistUrlRoute, SongUrlRoute, SongPlaylistUrlRoute, ContributorUrlRoute, SharedUrlRoute, PlaylistUrlRoute, MusicalGenreUrlRoute, HistoricalUrlRoute{

    public static final String URL_API = "/api";
    public static final String URL_LOGIN = "/security/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_REGISTER = "/security/register";
    public static final String URL_ADMIN = "/admin";
    public static final String URL_REFRESH = "/security/refresh";
    public static final String URL_UPLOAD = "/upload";
    public static final String URL_UPLOAD_IMAGE = URL_UPLOAD + "/image";
    public static final String URL_UPLOAD_SONG = URL_UPLOAD + "/song";

}