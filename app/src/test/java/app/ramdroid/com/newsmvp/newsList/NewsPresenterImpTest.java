package app.ramdroid.com.newsmvp.newsList;

import org.junit.Before;
import org.junit.Test;

import app.ramdroid.com.newsmvp.ui.newsList.NewsPresenterImp;
import app.ramdroid.com.newsmvp.ui.newsList.NewsView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ramadanmoustafa on 5/9/17.
 */

public class NewsPresenterImpTest {
    NewsPresenterImp newsPresenter;
    @Before
    public void setUp() throws Exception {
        newsPresenter = new NewsPresenterImp();
    }


    @Test
    public void onNewsItemClicked() throws Exception {
        NewsView newsView = mock(NewsView.class);
        newsPresenter.setView(newsView);
        newsPresenter.onNewsItemClicked("dummy url");
        verify(newsView).showErrorMessage("error");

    }

}