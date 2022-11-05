package com.hebaelsaid.android.newsapp.repository;


import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.EgyptNewsClient;
import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.EgyptNewsInterface;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EgyptNewsRepoImpl implements EgyptNewsInterface {

    @Override
    public Observable<EgyptNewsResponseModel> getEgyptNewsData(String country,String key) {
       return EgyptNewsClient.getINSTANCE().getEgyptNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
