package app.ramdroid.com.newsmvp.ui.newsList;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.ramdroid.com.newsmvp.app.NewsApplication;
import app.ramdroid.com.newsmvp.data.model.NewsEntity;
import app.ramdroid.com.newsmvp.data.model.NewsResponse;
import app.ramdroid.com.newsmvp.data.network.NewsApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ramadanmoustafa on 5/5/17.
 */

public class NewsPresenterImp implements NewsPresenter {
    private static final String TAG = NewsPresenterImp.class.getSimpleName();
    @Inject
    NewsApi mNewsApi;
    private NewsView mView;
    private List<NewsEntity> mNewsList = new ArrayList<>();
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void setView(NewsView view) {
        mView = view;
    }

    /**
     * loads data from a remote endpoint
     */
    @Override
    public void loadNewsData() {
        if (mView != null && mNewsList != null && mNewsList.size() > 0) {
            mView.populateNewsList(mNewsList);
            return;
        }
        NewsApplication.getAppComponent().inject(this);
        mView.showProgress();
        Log.e(TAG, "load");
        Observable<NewsResponse> newsObservable = mNewsApi.getNews();
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(newsObservable
                // Run on a background thread
                .subscribeOn(Schedulers.newThread())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<NewsResponse>() {

                    @Override
                    public void onNext(NewsResponse responseBody) {

                        Log.e(TAG, "onnext");
                        mView.hideProgress();
                        mNewsList.clear();
                        if (responseBody != null && responseBody.getNewsList() != null) {
                            mNewsList.addAll(responseBody.getNewsList());
                        }
                        mView.populateNewsList(mNewsList);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                        mView.hideProgress();
                        mView.showErrorMessage("error occured");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");

                    }
                }));
    }

    public List<NewsEntity> getNewsList() {
        return mNewsList;
    }

    @Override
    public void onNewsItemClicked(String newsTitle) {
        NewsEntity entity = null;
        for (NewsEntity item : mNewsList) {
            if (item.getTitle().equals(newsTitle)) {
                entity = item;
                break;
            }
        }
        if (entity != null)
            mView.openDetailsActivity(entity);
        else
            mView.showErrorMessage("error");

    }

    @Override
    public void onConfigurationChanged(NewsView view) {

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "ondestroy");
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();// do not send event after activity has been destroyed

    }
}
