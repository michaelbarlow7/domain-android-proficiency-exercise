package com.mbarlow.domainbondiproperties.injection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbarlow.domainbondiproperties.data.ListingRepository;
import com.mbarlow.domainbondiproperties.data.ListingRepositoryImpl;
import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by michael on 31/08/16.
 *
 */
public class Injection {
    private static final String BASE_URL = "https://rest.domain.com.au";

    private static DomainRestService domainRestService;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;

    public static ListingRepository provideListingRepository() {
        return new ListingRepositoryImpl(provideDomainRestService());
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
}
