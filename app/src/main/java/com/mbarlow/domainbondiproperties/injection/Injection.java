package com.mbarlow.domainbondiproperties.injection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbarlow.domainbondiproperties.data.ListingRepository;
import com.mbarlow.domainbondiproperties.data.ListingRepositoryImpl;
import com.mbarlow.domainbondiproperties.data.local.DatabaseHelper;
import com.mbarlow.domainbondiproperties.data.local.DatabaseHelperImpl;
import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;
import com.mbarlow.domainbondiproperties.event.EventBusProvider;
import com.mbarlow.domainbondiproperties.event.EventBusProviderImpl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A class which is used for managing dependencies. A poor-man's Dagger, so to speak :)
 *
 * Created by michael on 31/08/16.
 *
 */
public class Injection {
    private static final String BASE_URL = "https://rest.domain.com.au";

    private static DomainRestService domainRestService;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;
    private static DatabaseHelper databaseHelper;
    private static EventBusProvider eventBusProvider;

    public static ListingRepository provideListingRepository() {
        return new ListingRepositoryImpl(provideDomainRestService(), provideDatabaseHelper());
    }

    private static DomainRestService provideDomainRestService() {
        if (domainRestService == null) {
            domainRestService = getRetrofitInstance().create(DomainRestService.class);
        }
        return domainRestService;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY.UPPER_CAMEL_CASE)
                    .create();
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injection.getOkHttpClient()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();
        }
        return retrofitInstance;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
        }
        return okHttpClient;
    }

    public static DatabaseHelper provideDatabaseHelper(){
        if (databaseHelper == null){
            databaseHelper = new DatabaseHelperImpl();
        }
        return databaseHelper;
    }

    public static EventBusProvider provideEventBusProvider(){
        if (eventBusProvider == null){
            eventBusProvider = new EventBusProviderImpl();
        }
        return eventBusProvider;
    }
}
