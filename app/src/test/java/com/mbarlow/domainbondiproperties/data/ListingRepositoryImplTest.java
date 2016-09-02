package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.data.local.DatabaseHelper;
import com.mbarlow.domainbondiproperties.data.remote.ApiResponse;
import com.mbarlow.domainbondiproperties.data.remote.ApiResponse.ListingResults;
import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;
import com.mbarlow.domainbondiproperties.model.Listing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michael on 2/09/16.
 */
public class ListingRepositoryImplTest {

    public static final long LISTING1_AD_ID = 1L;
    public static final long LISTING2_AD_ID = 2L;
    // Mock out the rest service
    @Mock
    DomainRestService domainRestService;

    @Mock
    DatabaseHelper databaseHelper;

    private ListingRepository listingRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        listingRepository = new ListingRepositoryImpl(domainRestService, databaseHelper);
    }

    @Test
    public void refreshListings_StoresDataAndInvokesCorrectApiCall(){
        // Given
        when(domainRestService.getListings()).thenReturn(Observable.just(getFakeApiResponse()));

        // When
        TestSubscriber<List<Listing>> subscriber = new TestSubscriber<>();
        listingRepository.refreshListings().subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<List<Listing>> onNextEvents = subscriber.getOnNextEvents();
        List<Listing> listings = onNextEvents.get(0);
        verify(databaseHelper).replaceListings(listings);
        Assert.assertEquals(LISTING1_AD_ID, listings.get(0).getAdId());
        Assert.assertEquals(LISTING2_AD_ID, listings.get(1).getAdId());
    }

    @Test
    public void getListings_RetrievesFromDatabaseHelper(){
        //Given
        when(databaseHelper.getAllListings()).thenReturn(getFakeListings());

        // When
        TestSubscriber<List<Listing>> subscriber = new TestSubscriber<>();
        listingRepository.getListings().subscribe(subscriber);

        // Then
        verify(databaseHelper).getAllListings();

    }

    private ApiResponse getFakeApiResponse() {
        ListingResults listingResults = new ListingResults();
        listingResults.setListings(getFakeListings());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setListingResults(listingResults);

        return apiResponse;
    }

    private List<Listing> getFakeListings() {
        Listing listing1 = new Listing();
        listing1.setAdId(LISTING1_AD_ID);
        listing1.setAgencyLogoUrl("http://www.test1agencylogo.com");
        listing1.setBathrooms(1);
        listing1.setBedrooms(1);
        listing1.setCarspaces(1);
        listing1.setDisplayPrice("$1");
        listing1.setDisplayableAddress("1 Test Avenue");
        listing1.setTruncatedDescription("The first test listing");
        listing1.setRetinaDisplayThumbUrl("http://www.test1retinaurl.com");
        listing1.setSecondRetinaDisplayThumbUrl("http://www.test1secondRetinaurl.com");
        listing1.setIsElite(false);

        Listing listing2 = new Listing();
        listing2.setAdId(LISTING2_AD_ID);
        listing2.setAgencyLogoUrl("http://www.test2agencylogo.com");
        listing2.setBathrooms(2);
        listing2.setBedrooms(2);
        listing2.setCarspaces(2);
        listing2.setDisplayPrice("$2");
        listing2.setDisplayableAddress("2 Test Avenue");
        listing2.setTruncatedDescription("The second test listing");
        listing2.setRetinaDisplayThumbUrl("http://www.test2retinaurl.com");
        listing2.setSecondRetinaDisplayThumbUrl("http://www.test2secondRetinaurl.com");
        listing2.setIsElite(true);

        List<Listing> listings = new ArrayList<>();
        listings.add(listing1);
        listings.add(listing2);

        return listings;
    }


}