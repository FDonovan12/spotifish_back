package fr.donovan.spotifish.mapping;

public interface SharedUrlRoute {

    String URL_SHARED = "/shared";
    String URL_SHARED_NEW = URL_SHARED + "/new";
    String URL_SHARED_EDIT = URL_SHARED + "/edit";
    String URL_SHARED_DELETE = URL_SHARED + "/delete";

    String URL_ADMIN_SHARED = "/admin" + URL_SHARED;
    String URL_ADMIN_SHARED_NEW = URL_ADMIN_SHARED + "/new";
    String URL_ADMIN_SHARED_EDIT = URL_ADMIN_SHARED + "/edit";
    String URL_ADMIN_SHARED_DELETE = URL_ADMIN_SHARED + "/delete";
}