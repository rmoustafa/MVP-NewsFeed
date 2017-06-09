package app.ramdroid.com.newsmvp.di.module;

import javax.inject.Singleton;

import app.ramdroid.com.newsmvp.app.NewsApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ramadanmoustafa on 5/6/17.
 */

@Module
public class AppModule {

    private NewsApplication mApplication;

    public AppModule(NewsApplication application){
        mApplication = application;
    }

    @Provides
    @Singleton
    public NewsApplication provideNewsApplication(){
        return mApplication;
    }

}
