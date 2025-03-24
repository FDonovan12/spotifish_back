package fr.donovan.spotifish.mapping;

public interface SongPlaylistUrlRoute {

    String URL_SONGPLAYLIST = "/song-playlist";
    String URL_SONGPLAYLIST_NEW = URL_SONGPLAYLIST + "/new";
    String URL_SONGPLAYLIST_EDIT = URL_SONGPLAYLIST + "/edit";
    String URL_SONGPLAYLIST_DELETE = URL_SONGPLAYLIST + "/delete";

    String URL_ADMIN_SONGPLAYLIST = "/admin" + URL_SONGPLAYLIST;
    String URL_ADMIN_SONGPLAYLIST_NEW = URL_ADMIN_SONGPLAYLIST + "/new";
    String URL_ADMIN_SONGPLAYLIST_EDIT = URL_ADMIN_SONGPLAYLIST + "/edit";
    String URL_ADMIN_SONGPLAYLIST_DELETE = URL_ADMIN_SONGPLAYLIST + "/delete";
}