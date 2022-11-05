package com.hebaelsaid.android.newsapp.domain.data.remote.app_sevice;

import androidx.annotation.NonNull;

import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.EgyptNewsInterface;
import com.hebaelsaid.android.newsapp.domain.data.remote.latest_news.LatestNewsInterface;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClientService {
    private static  Retrofit retrofit
            = null;
    private final String apiKey = "14e5e0dc7d9049daaf1b8fa74a5838fd";

    public AppClientService(){
       retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .baseUrl("https://newsapi.org/v2/")
               /*.client(new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                   @NonNull
                   @Override
                   public Response intercept(@NonNull Chain chain) throws IOException {
                       HttpUrl url  = chain.request()
                               .url()
                               .newBuilder()
                               .addQueryParameter("apiKey", apiKey)
                               .build();
                       chain.proceed(chain.request().newBuilder().url(url).build());
                       return null;
                   }
               }).build())*/
               .build();
    }
    public EgyptNewsInterface getEgyptNewsInstance(){
       return retrofit.create(EgyptNewsInterface.class);
    }
    public LatestNewsInterface getLatestNewsInstance(){
       return retrofit.create(LatestNewsInterface.class);
    }


}
