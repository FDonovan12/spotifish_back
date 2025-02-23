package fr.donovan.spotifish.mapping;

public interface ContributorUrlRoute {

    String URL_CONTRIBUTOR = "/contributor";
    String URL_CONTRIBUTOR_NEW = URL_CONTRIBUTOR + "/new";
    String URL_CONTRIBUTOR_EDIT = URL_CONTRIBUTOR + "/edit";
    String URL_CONTRIBUTOR_DELETE = URL_CONTRIBUTOR + "/delete";

    String URL_ADMIN_CONTRIBUTOR = "/admin" + URL_CONTRIBUTOR;
    String URL_ADMIN_CONTRIBUTOR_NEW = URL_ADMIN_CONTRIBUTOR + "/new";
    String URL_ADMIN_CONTRIBUTOR_EDIT = URL_ADMIN_CONTRIBUTOR + "/edit";
    String URL_ADMIN_CONTRIBUTOR_DELETE = URL_ADMIN_CONTRIBUTOR + "/delete";
}