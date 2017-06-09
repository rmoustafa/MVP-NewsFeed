package app.ramdroid.com.newsmvp.ui.newsList;


/**
 *  MVP Presenter interface for {@link NewsPresenterImp}.
 */
public interface NewsPresenter {
    void setView(NewsView view);
    void loadNewsData();
    void onNewsItemClicked(String newsTitle);
    void onConfigurationChanged(NewsView view);
    void onDestroy();

}
