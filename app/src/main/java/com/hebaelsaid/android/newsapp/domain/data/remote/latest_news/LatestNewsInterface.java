package com.hebaelsaid.android.newsapp.domain.data.remote.latest_news;

import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LatestNewsInterface {
    @GET("top-headlines/sources")
    public Observable<LatestNewsResponseModel> getAllNewsData(
            @Query("apiKey") String key
    );
}
