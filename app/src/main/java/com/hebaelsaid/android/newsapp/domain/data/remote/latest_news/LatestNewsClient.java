package com.hebaelsaid.android.newsapp.domain.data.remote.latest_news;

import com.hebaelsaid.android.newsapp.domain.data.remote.app_sevice.AppClientService;
import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;

import io.reactivex.Observable;

public class LatestNewsClient {
    private static LatestNewsClient INSTANCE;
    private AppClientService clientService ;
    private LatestNewsInterface latestNewsInterface;
    public LatestNewsClient() {
        clientService = new AppClientService();
        latestNewsInterface =  clientService.getLatestNewsInstance();
    }
    public static LatestNewsClient getINSTANCE() {
        if(INSTANCE == null ){
            INSTANCE = new LatestNewsClient();
        }
        return INSTANCE;
    }
    public Observable<LatestNewsResponseModel> getAllNews(){
        return latestNewsInterface.getAllNewsData("14e5e0dc7d9049daaf1b8fa74a5838fd");
    }
}
