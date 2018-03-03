package org.dalol.ethiomixtube.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.dalol.ethiomixtube.R;
import org.dalol.ethiomixtube.data.YoutubeApi;
import org.dalol.ethiomixtube.data.vo.VideoVO;
import org.dalol.ethiomixtube.presenter.VideoPresenter;
import org.dalol.ethiomixtube.ui.MainVideoView;
import org.dalol.ethiomixtube.ui.common.LinearItemsMarginDecorator;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainVideoView {

    private VideoListAdapter mVideoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);

        VideoPresenter presenter = new VideoPresenter(this, youtubeApi);

        RecyclerView videoList = (RecyclerView) findViewById(R.id.list_videos);
        videoList.setHasFixedSize(true);
        videoList.setLayoutManager(new LinearLayoutManager(this));
        videoList.addItemDecoration(new LinearItemsMarginDecorator(getResources().getDimensionPixelSize(R.dimen.size_item_gap)));

        mVideoListAdapter = new VideoListAdapter(getLayoutInflater());
        videoList.setAdapter(mVideoListAdapter);


        String hopeMusic = "hoplessable";
        String myChannelName = "UCaJhhhbN8cxAk05QqraQPxA";
        String amharicLyrics = "AhaduWubuZewdie";

        presenter.getVideoInfo(amharicLyrics, YoutubeApi.YOUTUBE_API_KEY);
    }

    @Override
    public void onLoadVideoList(List<VideoVO> videoVOs) {
        mVideoListAdapter.addVideoItems(videoVOs);
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
