package fr.donovan.spotifish.mapping;

public interface ArtistUrlRoute {

    String URL_ARTIST = "/artist";
    String URL_ARTIST_NEW = URL_ARTIST + "/new";
    String URL_ARTIST_EDIT = URL_ARTIST + "/edit";
    String URL_ARTIST_DELETE = URL_ARTIST + "/delete";

    String URL_ADMIN_ARTIST = "/admin" + URL_ARTIST;
    String URL_ADMIN_ARTIST_NEW = URL_ADMIN_ARTIST + "/new";
    String URL_ADMIN_ARTIST_EDIT = URL_ADMIN_ARTIST + "/edit";
    String URL_ADMIN_ARTIST_DELETE = URL_ADMIN_ARTIST + "/delete";
}