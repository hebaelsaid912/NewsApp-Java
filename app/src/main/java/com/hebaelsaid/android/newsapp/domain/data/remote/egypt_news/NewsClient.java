package com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news;

import com.hebaelsaid.android.newsapp.domain.data.remote.app_sevice.AppClientService;
import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;

import io.reactivex.Observable;

public class NewsClient {
    private static NewsClient INSTANCE;
    private AppClientService clientService ;
    private NewsInterface newsInterface;
    public NewsClient() {
        clientService = new AppClientService();
       newsInterface =  clientService.getEgyptNewsInstance();
    }
    public static NewsClient getINSTANCE() {
        if(INSTANCE == null ){
            INSTANCE = new NewsClient();
        }
        return INSTANCE;
    }
    public Observable<NewsResponseModel> getEgyptNews(String country){
        return newsInterface.getEgyptNewsData(country,"14e5e0dc7d9049daaf1b8fa74a5838fd");
    }
    public Observable<NewsResponseModel> getAllNews(String source){
        return newsInterface.getAllNewsData(source,"14e5e0dc7d9049daaf1b8fa74a5838fd");
    }
}