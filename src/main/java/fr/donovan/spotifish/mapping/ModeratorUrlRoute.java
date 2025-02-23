package fr.donovan.spotifish.mapping;

public interface ModeratorUrlRoute {

    String URL_MODERATOR = "/moderator";
    String URL_MODERATOR_NEW = URL_MODERATOR + "/new";
    String URL_MODERATOR_EDIT = URL_MODERATOR + "/edit";
    String URL_MODERATOR_DELETE = URL_MODERATOR + "/delete";

    String URL_ADMIN_MODERATOR = "/admin" + URL_MODERATOR;
    String URL_ADMIN_MODERATOR_NEW = URL_ADMIN_MODERATOR + "/new";
    String URL_ADMIN_MODERATOR_EDIT = URL_ADMIN_MODERATOR + "/edit";
    String URL_ADMIN_MODERATOR_DELETE = URL_ADMIN_MODERATOR + "/delete";
}