package fr.donovan.spotifish.mapping;

public interface SongUrlRoute {

    String URL_SONG = "/song";
    String URL_SONG_NEW = URL_SONG + "/new";
    String URL_SONG_EDIT = URL_SONG + "/edit";
    String URL_SONG_DELETE = URL_SONG + "/delete";

    String URL_ADMIN_SONG = "/admin" + URL_SONG;
    String URL_ADMIN_SONG_NEW = URL_ADMIN_SONG + "/new";
    String URL_ADMIN_SONG_EDIT = URL_ADMIN_SONG + "/edit";
    String URL_ADMIN_SONG_DELETE = URL_ADMIN_SONG + "/delete";
}