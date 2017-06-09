package app.ramdroid.com.newsmvp.di.component;

import javax.inject.Singleton;

import app.ramdroid.com.newsmvp.di.module.AppModule;
import app.ramdroid.com.newsmvp.di.module.PresenterModule;
import app.ramdroid.com.newsmvp.di.module.ServiceModule;
import app.ramdroid.com.newsmvp.ui.newsList.NewsActivity;
import app.ramdroid.com.newsmvp.ui.newsList.NewsPresenterImp;
import dagger.Component;

/**
 * Created by ramadanmoustafa on 5/6/17.
 */

@Singleton
@Component(modules = { AppModule.class, PresenterModule.class, ServiceModule.class})
public interface AppComponent {
    void inject(NewsActivity activity);
    void inject(NewsPresenterImp newsPresenterImp);
}
