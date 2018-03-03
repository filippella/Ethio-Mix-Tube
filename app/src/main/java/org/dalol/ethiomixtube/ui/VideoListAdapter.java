package org.dalol.ethiomixtube.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.dalol.ethiomixtube.R;
import org.dalol.ethiomixtube.data.vo.VideoVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 05/11/2017.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private final List<VideoVO> mVideoVOs = new LinkedList<>();
    private final LayoutInflater mLayoutInflater;

    public VideoListAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(mLayoutInflater.inflate(R.layout.item_video_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.bind(mVideoVOs.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideoVOs.size();
    }

    void addVideoItem(VideoVO videoVO) {
        mVideoVOs.add(videoVO);
        notifyDataSetChanged();
    }

    void addVideoItems(List<VideoVO> videoVOs) {
        mVideoVOs.addAll(videoVOs);
        notifyDataSetChanged();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView videoThumbnail;
        final TextView videoTitle;
        final TextView videoPublishDate;
        final TextView videoDescription;

        private VideoViewHolder(View itemView) {
            super(itemView);
            videoThumbnail = (ImageView) itemView.findViewById(R.id.image_video_thumbnail);
            videoTitle = (TextView) itemView.findViewById(R.id.text_video_title);
            videoDescription = (TextView) itemView.findViewById(R.id.text_video_description);
            videoPublishDate = (TextView) itemView.findViewById(R.id.text_video_publish_date);
            itemView.setOnClickListener(this);
        }

        public void bind(VideoVO videoVO) {
            Context context = videoThumbnail.getContext();
            videoTitle.setText(videoVO.mVideoTitle);
            videoDescription.setText(videoVO.mVideoDescription);
            videoPublishDate.setText(videoVO.mVideoPublishDate);
            Glide.with(context.getApplicationContext()).load(videoVO.mVideoThumbnailURL).into(videoThumbnail);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
