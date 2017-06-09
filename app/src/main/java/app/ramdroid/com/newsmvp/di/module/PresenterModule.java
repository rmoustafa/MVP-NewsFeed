package app.ramdroid.com.newsmvp.di.module;

import javax.inject.Singleton;

import app.ramdroid.com.newsmvp.ui.newsList.NewsPresenter;
import app.ramdroid.com.newsmvp.ui.newsList.NewsPresenterImp;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ramadanmoustafa on 5/8/17.
 */

@Module
public class PresenterModule {
    @Provides
    @Singleton
    public NewsPresenter provideNewsPresenter(){
        return new NewsPresenterImp();
    }
}
