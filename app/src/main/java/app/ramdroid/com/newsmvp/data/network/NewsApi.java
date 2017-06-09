package app.ramdroid.com.newsmvp.data.network;

import app.ramdroid.com.newsmvp.data.model.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * This interface contains methods we are going to use to execute HTTP requests
 * such as GET, POST, PUT, PATCH, and DELETE.
 * For our case, I am going to execute only a single GET request.
 *
 * Created by ramadanmoustafa on 5/3/17.
 */

public interface NewsApi {

    @GET("news.json")
    Observable<NewsResponse> getNews();
}
