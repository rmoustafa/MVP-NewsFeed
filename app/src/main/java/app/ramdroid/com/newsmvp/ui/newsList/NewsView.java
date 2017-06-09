package app.ramdroid.com.newsmvp.ui.newsList;

import java.util.List;

import app.ramdroid.com.newsmvp.data.model.NewsEntity;


/**
 *  MVP view interface for {@link NewsActivity}.
 */
public interface NewsView {
    /**
     * would be callled once the news data is returned
     * to populate them to the list adatpter
     * @param newsDataList
     */
    void populateNewsList(List<NewsEntity> newsDataList);
    void showErrorMessage(String message);
    void openDetailsActivity(NewsEntity entity);
    void showProgress();
    void hideProgress();

}
