package app.ramdroid.com.newsmvp.app;

import android.app.Application;

import app.ramdroid.com.newsmvp.di.component.AppComponent;
import app.ramdroid.com.newsmvp.di.component.DaggerAppComponent;
import app.ramdroid.com.newsmvp.di.module.AppModule;


/**
 * Created by ramadanmoustafa on 5/6/17.
 */

public class NewsApplication extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
