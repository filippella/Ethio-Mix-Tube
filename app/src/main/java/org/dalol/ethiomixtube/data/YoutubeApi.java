package org.dalol.ethiomixtube.data;

import org.dalol.ethiomixtube.data.model.channel.ChannelResponse;
import org.dalol.ethiomixtube.data.model.videos.VideoListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by filippo on 05/11/2017.
 */

public interface YoutubeApi {

    String YOUTUBE_API_KEY = "AIzaSyD-xeBAxM3ibxESPfMyvysOoHAelUtGbVc";

    @GET("/youtube/v3/channels?part=snippet")
    Observable<ChannelResponse> getChannel(@Query("forUsername") String channelName, @Query("key") String apiKey);


    @GET("/youtube/v3/search?order=date&part=snippet&maxResults=50")
    Observable<VideoListResponse> getVideoList(@Query("channelId") String channelId, @Query("key") String apiKey);

    @GET("/youtube/v3/search?order=date&part=snippet&maxResults=25")
    Observable<VideoListResponse> getNextVideoList(@Query("channelId") String channelId, @Query("pageInfo") String pageInfo, @Query("key") String apiKey);
}
