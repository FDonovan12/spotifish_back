package fr.donovan.spotifish.mapping;

public interface AlbumUrlRoute {

    String URL_ALBUM = "/album";
    String URL_ALBUM_NEW = URL_ALBUM + "/new";
    String URL_ALBUM_EDIT = URL_ALBUM + "/edit";
    String URL_ALBUM_DELETE = URL_ALBUM + "/delete";

    String URL_ADMIN_ALBUM = "/admin" + URL_ALBUM;
    String URL_ADMIN_ALBUM_NEW = URL_ADMIN_ALBUM + "/new";
    String URL_ADMIN_ALBUM_EDIT = URL_ADMIN_ALBUM + "/edit";
    String URL_ADMIN_ALBUM_DELETE = URL_ADMIN_ALBUM + "/delete";
}