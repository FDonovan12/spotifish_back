package fr.donovan.spotifish.mapping;

public interface SongArtistUrlRoute {

    String URL_SONGARTIST = "/songartist";
    String URL_SONGARTIST_NEW = URL_SONGARTIST + "/new";
    String URL_SONGARTIST_EDIT = URL_SONGARTIST + "/edit";
    String URL_SONGARTIST_DELETE = URL_SONGARTIST + "/delete";

    String URL_ADMIN_SONGARTIST = "/admin" + URL_SONGARTIST;
    String URL_ADMIN_SONGARTIST_NEW = URL_ADMIN_SONGARTIST + "/new";
    String URL_ADMIN_SONGARTIST_EDIT = URL_ADMIN_SONGARTIST + "/edit";
    String URL_ADMIN_SONGARTIST_DELETE = URL_ADMIN_SONGARTIST + "/delete";
}