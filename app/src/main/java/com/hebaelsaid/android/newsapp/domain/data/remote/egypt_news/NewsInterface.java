package com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news;

import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

public interface NewsInterface {

    @GET("top-headlines")
    public Observable<NewsResponseModel> getEgyptNewsData(
            @Query("country") String country,
            @Query("apiKey") String key
    );

    @GET("top-headlines")
    public Observable<NewsResponseModel> getAllNewsData(
            @Query("sources") String source,
            @Query("apiKey") String key
    );

}
