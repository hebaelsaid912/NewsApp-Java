package com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news;

import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;

public interface EgyptNewsInterface {

    @GET("top-headlines")
    public Observable<EgyptNewsResponseModel> getEgyptNewsData(
            @Query("country") String country,
            @Query("apiKey") String key
    );

}
