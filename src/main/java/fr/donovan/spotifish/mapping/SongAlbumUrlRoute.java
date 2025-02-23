package fr.donovan.spotifish.mapping;

public interface SongAlbumUrlRoute {

    String URL_SONGALBUM = "/songalbum";
    String URL_SONGALBUM_NEW = URL_SONGALBUM + "/new";
    String URL_SONGALBUM_EDIT = URL_SONGALBUM + "/edit";
    String URL_SONGALBUM_DELETE = URL_SONGALBUM + "/delete";

    String URL_ADMIN_SONGALBUM = "/admin" + URL_SONGALBUM;
    String URL_ADMIN_SONGALBUM_NEW = URL_ADMIN_SONGALBUM + "/new";
    String URL_ADMIN_SONGALBUM_EDIT = URL_ADMIN_SONGALBUM + "/edit";
    String URL_ADMIN_SONGALBUM_DELETE = URL_ADMIN_SONGALBUM + "/delete";
}