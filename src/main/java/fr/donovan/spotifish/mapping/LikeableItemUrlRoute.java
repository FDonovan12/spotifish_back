package fr.donovan.spotifish.mapping;

public interface LikeableItemUrlRoute {

    String URL_LIKEABLEITEM = "/likeableitem";
    String URL_LIKEABLEITEM_NEW = URL_LIKEABLEITEM + "/new";
    String URL_LIKEABLEITEM_EDIT = URL_LIKEABLEITEM + "/edit";
    String URL_LIKEABLEITEM_DELETE = URL_LIKEABLEITEM + "/delete";

    String URL_ADMIN_LIKEABLEITEM = "/admin" + URL_LIKEABLEITEM;
    String URL_ADMIN_LIKEABLEITEM_NEW = URL_ADMIN_LIKEABLEITEM + "/new";
    String URL_ADMIN_LIKEABLEITEM_EDIT = URL_ADMIN_LIKEABLEITEM + "/edit";
    String URL_ADMIN_LIKEABLEITEM_DELETE = URL_ADMIN_LIKEABLEITEM + "/delete";
}