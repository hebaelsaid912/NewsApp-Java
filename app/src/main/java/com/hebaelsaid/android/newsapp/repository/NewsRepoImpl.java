package com.hebaelsaid.android.newsapp.repository;


import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.NewsClient;
import com.hebaelsaid.android.newsapp.domain.data.remote.egypt_news.NewsInterface;
import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsRepoImpl implements NewsInterface {

    @Override
    public Observable<NewsResponseModel> getEgyptNewsData(String country, String key) {
       return NewsClient.getINSTANCE().getEgyptNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public Observable<NewsResponseModel> getAllNewsData(String source, String key) {
        return NewsClient.getINSTANCE().getAllNews(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
