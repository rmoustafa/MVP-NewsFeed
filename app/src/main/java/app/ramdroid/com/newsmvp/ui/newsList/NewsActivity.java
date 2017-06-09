package app.ramdroid.com.newsmvp.ui.newsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import javax.inject.Inject;

import app.ramdroid.com.newsmvp.R;
import app.ramdroid.com.newsmvp.app.NewsApplication;
import app.ramdroid.com.newsmvp.data.model.NewsEntity;
import app.ramdroid.com.newsmvp.ui.newsDetails.NewsDetailActivity;
import app.ramdroid.com.newsmvp.ui.newsDetails.NewsDetailFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NewsDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class NewsActivity extends AppCompatActivity implements NewsView, NewsAdapter.OnNewsItemClickListener{

    private static final String TAG = NewsActivity.class.getSimpleName();

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    @Inject
    NewsPresenter mNewsPresenter;
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private NewsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);
        Fresco.initialize(this);
        initRecyclerView();
        Log.e(TAG, "oncreate");

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w600dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        NewsApplication.getAppComponent().inject(this);
        mNewsPresenter.setView(this);
        mNewsPresenter.loadNewsData();
    }

    /**
     * Inites the recyclerview & adds a divider between items
     */
    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new NewsAdapter(NewsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onNewsItemClicked(String newsTitle) {
        //delegates the presenter to handle the logic of the click
        mNewsPresenter.onNewsItemClicked(newsTitle);
    }


    @Override
    public void populateNewsList(List<NewsEntity> newsDataList) {
        mAdapter.updateDataSet(newsDataList);

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void openDetailsActivity(NewsEntity entity) {
        String storyURL = entity.getArticleUrl();
        String title = entity.getTitle();
        String summary = entity.getSummary();
        String imageURL = "";
        if(entity.getMultimedia() != null && entity.getMultimedia().size() >0)
            imageURL= entity.getMultimedia().get(0).getUrl();

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(NewsDetailFragment.ARG_STORY_URL, storyURL);
            arguments.putString(NewsDetailFragment.ARG_TITLE, title);
            arguments.putString(NewsDetailFragment.ARG_SUMMARY, summary);
            arguments.putString(NewsDetailFragment.ARG_IMAGE_URL, imageURL);
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
            intent.putExtra(NewsDetailFragment.ARG_STORY_URL, storyURL);
            intent.putExtra(NewsDetailFragment.ARG_TITLE, title);
            intent.putExtra(NewsDetailFragment.ARG_SUMMARY, summary);
            intent.putExtra(NewsDetailFragment.ARG_IMAGE_URL, imageURL);

            startActivity(intent);
        }

    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        mNewsPresenter.onDestroy();
    }
}
