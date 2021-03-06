package com.mbarlow.domainbondiproperties.data.remote;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by michael on 31/08/16.
 *
 * The interface for the Domain REST API, used by RetroFit
 */
public interface DomainRestService {

    @GET("/searchservice.svc/mapsearch?mode=buy&sub=Bondi&pcodes=2026&state=NSW")
    Observable<ApiResponse> getListings();
}
