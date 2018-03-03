package org.dalol.ethiomixtube.ui;

import org.dalol.ethiomixtube.data.vo.VideoVO;
import org.dalol.ethiomixtube.ui.base.BaseView;

import java.util.List;

/**
 * Created by filippo on 05/11/2017.
 */

public interface MainVideoView extends BaseView {

    void onLoadVideoList(List<VideoVO> videoVOs);

    void onShowToast(String message);
}
