package com.hebaelsaid.android.newsapp.domain.data.remote.app_sevice;

import androidx.annotation.NonNull;

import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.NewsInterface;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClientService {
    private static  Retrofit retrofit = null;
    private final String apiKey = "0273320d02d2456a8efee7b35caded59";

    public AppClientService(){
       retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .baseUrl("https://newsapi.org/v2/")
               .client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                   @NonNull
                   @Override
                   public Response intercept(@NonNull Chain chain) throws IOException {
                       HttpUrl url  = chain.request()
                               .url()
                               .newBuilder()
                               .addQueryParameter("apiKey", apiKey)
                               .build();

                       return chain.proceed(chain.request().newBuilder().url(url).build());
                   }
               }).build())
               .build();
    }
    public NewsInterface getEgyptNewsInstance(){
       return retrofit.create(NewsInterface.class);
    }


}
