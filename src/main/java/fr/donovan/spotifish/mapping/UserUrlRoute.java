package fr.donovan.spotifish.mapping;

public interface UserUrlRoute {

    String URL_USER = "/user";
    String URL_USER_NEW = URL_USER + "/new";
    String URL_USER_EDIT = URL_USER + "/edit";
    String URL_USER_DELETE = URL_USER + "/delete";

    String URL_ADMIN_USER = "/admin" + URL_USER;
    String URL_ADMIN_USER_NEW = URL_ADMIN_USER + "/new";
    String URL_ADMIN_USER_EDIT = URL_ADMIN_USER + "/edit";
    String URL_ADMIN_USER_DELETE = URL_ADMIN_USER + "/delete";
}