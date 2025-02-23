package fr.donovan.spotifish.mapping;

public interface HistoricalUrlRoute {

    String URL_HISTORICAL = "/historical";
    String URL_HISTORICAL_NEW = URL_HISTORICAL + "/new";
    String URL_HISTORICAL_EDIT = URL_HISTORICAL + "/edit";
    String URL_HISTORICAL_DELETE = URL_HISTORICAL + "/delete";

    String URL_ADMIN_HISTORICAL = "/admin" + URL_HISTORICAL;
    String URL_ADMIN_HISTORICAL_NEW = URL_ADMIN_HISTORICAL + "/new";
    String URL_ADMIN_HISTORICAL_EDIT = URL_ADMIN_HISTORICAL + "/edit";
    String URL_ADMIN_HISTORICAL_DELETE = URL_ADMIN_HISTORICAL + "/delete";
}