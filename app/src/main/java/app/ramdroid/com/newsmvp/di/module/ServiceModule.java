package app.ramdroid.com.newsmvp.di.module;

import javax.inject.Singleton;

import app.ramdroid.com.newsmvp.data.network.NewsApi;
import app.ramdroid.com.newsmvp.data.network.RetrofitClient;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ramadanmoustafa on 5/6/17.
 */

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public NewsApi provideNewsApi(){
        return new RetrofitClient().getsNewsApi();
    }
}
