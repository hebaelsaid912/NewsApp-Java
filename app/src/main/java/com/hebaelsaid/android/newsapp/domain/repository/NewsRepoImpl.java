package com.hebaelsaid.android.newsapp.domain.repository;


import com.hebaelsaid.android.newsapp.data.newsclient.NewsClient;
import com.hebaelsaid.android.newsapp.data.newsclient.NewsInterface;
import com.hebaelsaid.android.newsapp.data.model.NewsResponseModel;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsRepoImpl implements NewsInterface {

    @Override
    public Observable<NewsResponseModel> getEgyptNewsData(String country) {
       return NewsClient.getINSTANCE().getEgyptNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public Observable<NewsResponseModel> getAllNewsData(String source) {
        return NewsClient.getINSTANCE().getAllNews(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<NewsResponseModel> searchAllNewsData(String keyword) {
        return NewsClient.getINSTANCE().searchAllNews(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
