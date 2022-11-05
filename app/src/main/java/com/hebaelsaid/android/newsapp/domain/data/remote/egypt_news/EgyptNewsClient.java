package com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news;

import com.hebaelsaid.android.newsapp.domain.data.remote.app_sevice.AppClientService;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;

import io.reactivex.Observable;

public class EgyptNewsClient {
    private static EgyptNewsClient INSTANCE;
    private AppClientService clientService ;
    private EgyptNewsInterface egyptNewsInterface;
    public EgyptNewsClient() {
        clientService = new AppClientService();
       egyptNewsInterface =  clientService.getEgyptNewsInstance();
    }
    public static EgyptNewsClient getINSTANCE() {
        if(INSTANCE == null ){
            INSTANCE = new EgyptNewsClient();
        }
        return INSTANCE;
    }
    public Observable<EgyptNewsResponseModel> getEgyptNews(String country){
        return egyptNewsInterface.getEgyptNewsData(country,"14e5e0dc7d9049daaf1b8fa74a5838fd");
    }
}
