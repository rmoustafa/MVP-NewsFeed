package app.ramdroid.com.newsmvp.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramadanmoustafa on 5/3/17.
 */

public class RetrofitClient {

    private static final String NEWS_SERVICE_BASE_URL = "https://raw.githubusercontent.com/rmoustafa/json-data/master/";

    public NewsApi getsNewsApi() {

        return new Retrofit.Builder()
                .baseUrl(NEWS_SERVICE_BASE_URL)
                .addConverterFactory( GsonConverterFactory.create()) //used for well-structured json data
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(NewsApi.class);
    }
}
