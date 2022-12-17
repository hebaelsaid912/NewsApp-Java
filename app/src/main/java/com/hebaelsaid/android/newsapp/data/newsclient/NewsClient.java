package com.hebaelsaid.android.newsapp.data.newsclient;

import com.hebaelsaid.android.newsapp.data.appservice.AppClientService;
import com.hebaelsaid.android.newsapp.data.model.NewsResponseModel;

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
        return newsInterface.getEgyptNewsData(country);
    }
    public Observable<NewsResponseModel> getAllNews(String source){
        return newsInterface.getAllNewsData(source);
    }
    public Observable<NewsResponseModel> searchAllNews(String keyword){
        return newsInterface.searchAllNewsData(keyword);
    }
}
