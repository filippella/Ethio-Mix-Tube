package org.dalol.ethiomixtube.presenter;

import org.dalol.ethiomixtube.data.YoutubeApi;
import org.dalol.ethiomixtube.data.mapper.VideoMapper;
import org.dalol.ethiomixtube.data.model.channel.ChannelResponse;
import org.dalol.ethiomixtube.data.model.channel.Items;
import org.dalol.ethiomixtube.data.model.videos.VideoListResponse;
import org.dalol.ethiomixtube.data.vo.VideoVO;
import org.dalol.ethiomixtube.presenter.base.BasePresenter;
import org.dalol.ethiomixtube.presenter.common.AbstractFunction;
import org.dalol.ethiomixtube.ui.MainVideoView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by filippo on 05/11/2017.
 */

public class VideoPresenter extends BasePresenter<MainVideoView> {

    private final YoutubeApi mYoutubeApi;

    public VideoPresenter(MainVideoView view, YoutubeApi youtubeApi) {
        super(view);
        mYoutubeApi = youtubeApi;
    }

    public void getVideoInfo(String channelName, String apiKey) {
        Observable<List<VideoVO>> observable = mYoutubeApi.getChannel(channelName, apiKey)
                .flatMap(new AbstractFunction<String, ChannelResponse, ObservableSource<VideoListResponse>>(channelName) {
                    @Override
                    protected ObservableSource<VideoListResponse> apply(String subject, ChannelResponse input) {
                        String channelId = subject;
                        Items[] items = input.getItems();
                        if (items != null) {
                            channelId = items[0].getId();
                        }
                        return mYoutubeApi.getVideoList(channelId, YoutubeApi.YOUTUBE_API_KEY);
                    }
                })
                .map(new Function<VideoListResponse, List<VideoVO>>() {
                    @Override
                    public List<VideoVO> apply(@NonNull VideoListResponse videoListResponse) throws Exception {
                        return new VideoMapper().map(videoListResponse);
                    }
                });
        subscribe(observable, new DisposableObserver<List<VideoVO>>() {
            @Override
            public void onNext(@NonNull List<VideoVO> videoVOs) {
                MainVideoView view = getView();
                if (view != null) {
                    view.onLoadVideoList(videoVOs);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().onShowToast(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().onShowToast("Loading completed!");
            }
        });
    }
}
