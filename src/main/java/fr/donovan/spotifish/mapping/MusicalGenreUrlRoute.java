package fr.donovan.spotifish.mapping;

public interface MusicalGenreUrlRoute {

    String URL_MUSICALGENRE = "/musicalgenre";
    String URL_MUSICALGENRE_NEW = URL_MUSICALGENRE + "/new";
    String URL_MUSICALGENRE_EDIT = URL_MUSICALGENRE + "/edit";
    String URL_MUSICALGENRE_DELETE = URL_MUSICALGENRE + "/delete";

    String URL_ADMIN_MUSICALGENRE = "/admin" + URL_MUSICALGENRE;
    String URL_ADMIN_MUSICALGENRE_NEW = URL_ADMIN_MUSICALGENRE + "/new";
    String URL_ADMIN_MUSICALGENRE_EDIT = URL_ADMIN_MUSICALGENRE + "/edit";
    String URL_ADMIN_MUSICALGENRE_DELETE = URL_ADMIN_MUSICALGENRE + "/delete";
}