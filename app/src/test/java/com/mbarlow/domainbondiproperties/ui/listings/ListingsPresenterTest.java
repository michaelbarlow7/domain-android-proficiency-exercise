package com.mbarlow.domainbondiproperties.ui.listings;

import com.mbarlow.domainbondiproperties.data.ListingRepository;
import com.mbarlow.domainbondiproperties.event.EventBusProvider;
import com.mbarlow.domainbondiproperties.model.Listing;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michael on 2/09/16.
 */
public class ListingsPresenterTest {
    public static final long LISTING1_AD_ID = 1L;
    public static final long LISTING2_AD_ID = 2L;

    @Mock
    private ListingRepository listingRepository;

    @Mock
    private ListingsContract.View view;

    @Mock
    private EventBusProvider eventBusProvider;

    private ListingsPresenter listingsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        listingsPresenter = new ListingsPresenter(listingRepository, Schedulers.immediate(), Schedulers.immediate(), eventBusProvider);

        when(eventBusProvider.provideEventBus()).thenReturn(Mockito.mock(EventBus.class));
        listingsPresenter.attachView(view);
    }

    @Test
    public void refreshListings_CallCorrectMethodsOnView(){
        // Given
        when(listingRepository.refreshListings()).thenReturn(Observable.just(getFakeListings()));
        when(listingRepository.getListings()).thenReturn(Observable.just(getFakeListings()));

        // When
        listingsPresenter.refreshListings();

        // Then
        verify(view).showLoading();
    }

    @Test
    public void refreshListings_Error_CallCorrectMethodsOnView(){
        // Given
        String errorMsg = "No internet";
        when(listingRepository.refreshListings()).thenReturn(Observable.error(new IOException(errorMsg)));

        // When
        listingsPresenter.refreshListings();

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showListings(anyList());
        verify(view).showError(errorMsg);
    }

    @Test
    public void getListings_CallCorrectMethodsOnView(){
        // Given
        when(listingRepository.refreshListings()).thenReturn(Observable.just(getFakeListings()));
        when(listingRepository.getListings()).thenReturn(Observable.just(getFakeListings()));

        // When
        listingsPresenter.getListings();

        // Then
        verify(view).showListings(getFakeListings());
    }

    private List<Listing> listings;

    private List<Listing> getFakeListings() {
        if (listings == null) {
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

            listings = new ArrayList<>();
            listings.add(listing1);
            listings.add(listing2);
        }

        return listings;
    }
}