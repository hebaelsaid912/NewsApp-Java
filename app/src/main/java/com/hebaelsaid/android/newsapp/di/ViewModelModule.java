package com.hebaelsaid.android.newsapp.di;


import com.hebaelsaid.android.newsapp.repository.NewsRepoImpl;


import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class ViewModelModule {

    @ViewModelScoped
    @Provides
    static NewsRepoImpl getNewsRepoImpl(){
        return new NewsRepoImpl();
    }
}
