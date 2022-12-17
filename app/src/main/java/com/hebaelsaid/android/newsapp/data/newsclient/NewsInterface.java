package com.hebaelsaid.android.newsapp.data.newsclient;

import com.hebaelsaid.android.newsapp.data.model.NewsResponseModel;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

public interface NewsInterface {

    @GET("top-headlines")
    public Observable<NewsResponseModel> getEgyptNewsData(
            @Query("country") String country
    );

    @GET("top-headlines")
    public Observable<NewsResponseModel> getAllNewsData(
            @Query("sources") String source
    );
    @GET("top-headlines")
    public Observable<NewsResponseModel> searchAllNewsData(
            @Query("q") String keyword
    );

}
