package org.dalol.ethiomixtube.data.mapper;

import org.dalol.ethiomixtube.data.model.videos.Default;
import org.dalol.ethiomixtube.data.model.videos.High;
import org.dalol.ethiomixtube.data.model.videos.Items;
import org.dalol.ethiomixtube.data.model.videos.Snippet;
import org.dalol.ethiomixtube.data.model.videos.Thumbnails;
import org.dalol.ethiomixtube.data.model.videos.VideoListResponse;
import org.dalol.ethiomixtube.data.vo.VideoVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 05/11/2017.
 */

public class VideoMapper {

    public List<VideoVO> map(VideoListResponse videoListResponse) {
        List<VideoVO> videoVOs = new LinkedList<>();
        if (videoListResponse != null) {
            Items[] items = videoListResponse.getItems();
            if (items != null) {
                for (Items item : items) {
                    Snippet snippet = item.getSnippet();
                    if (snippet != null) {
                        VideoVO videoVO = new VideoVO();
                        videoVO.mVideoTitle = snippet.getTitle();
                        videoVO.mVideoDescription = snippet.getDescription();
                        videoVO.mVideoPublishDate = snippet.getPublishedAt();
                        Thumbnails thumbnails = snippet.getThumbnails();
                        if (thumbnails != null) {
                            High high = thumbnails.getHigh();
                            if (high != null) {
                                videoVO.mVideoThumbnailURL = high.getUrl();
                            }
//                            Default aDefault = thumbnails.getDefault();
//                            if (aDefault != null) {
//                                videoVO.mVideoThumbnailURL = aDefault.getUrl();
//                            }
                        }
                        videoVOs.add(videoVO);
                    }
                }
            }
        }
        return videoVOs;
    }
}
