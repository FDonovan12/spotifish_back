package fr.donovan.spotifish.mapping;

public interface PlaylistUrlRoute {

    String URL_PLAYLIST = "/playlist";
    String URL_PLAYLIST_NEW = URL_PLAYLIST + "/new";
    String URL_PLAYLIST_EDIT = URL_PLAYLIST + "/edit";
    String URL_PLAYLIST_DELETE = URL_PLAYLIST + "/delete";

    String URL_ADMIN_PLAYLIST = "/admin" + URL_PLAYLIST;
    String URL_ADMIN_PLAYLIST_NEW = URL_ADMIN_PLAYLIST + "/new";
    String URL_ADMIN_PLAYLIST_EDIT = URL_ADMIN_PLAYLIST + "/edit";
    String URL_ADMIN_PLAYLIST_DELETE = URL_ADMIN_PLAYLIST + "/delete";
}