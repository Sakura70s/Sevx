package top.sakura70s.sevx.fragments.video;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;

public class DetailsVideoFragment extends Fragment {
    private Activity mainActivity;
    private String type;

    // 布局Id
    private TextView title ;
    private TextView make;
    private TextView director;
    private TextView screenWriter;
    private TextView series;
    private TextView amount;
    private TextView container;
    private TextView codeV;
    private TextView codeA;
    private TextView localFlag;
    private TextView remoteFlag;
    private TextView localUrl;
    private TextView remoteUrl;
    private TextView subType;
    private TextView subTeam;
    private TextView lastWatch;
    private TextView lastUpdate;
    private TextView remark;
    private ImageView mediaInfo;

    // 传入的数据
    private Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initData();
        this.initView();
        this.setData();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(SevxConsts.TYPE);
        }
    }

    private void initView(){
        // 初始化控件
        title = mainActivity.findViewById(R.id.details_video_title);
        make = mainActivity.findViewById(R.id.details_video_make);
        director = mainActivity.findViewById(R.id.details_video_director);
        screenWriter = mainActivity.findViewById(R.id.details_video_screen_writer);
        series = mainActivity.findViewById(R.id.details_video_series);
        amount = mainActivity.findViewById(R.id.details_video_amount);
        container = mainActivity.findViewById(R.id.details_video_container);
        codeV = mainActivity.findViewById(R.id.details_video_codev);
        codeA = mainActivity.findViewById(R.id.details_video_codea);
        localFlag = mainActivity.findViewById(R.id.details_video_local_flag);
        remoteFlag = mainActivity.findViewById(R.id.details_video_remote_flag);
        localUrl = mainActivity.findViewById(R.id.details_video_local_url);
        remoteUrl = mainActivity.findViewById(R.id.details_video_remote_url);
        subType = mainActivity.findViewById(R.id.details_video_sub_type);
        subTeam = mainActivity.findViewById(R.id.details_video_sub_team);
        lastWatch = mainActivity.findViewById(R.id.details_video_last_watch);
        lastUpdate = mainActivity.findViewById(R.id.details_video_last_update);
        remark = mainActivity.findViewById(R.id.details_video_remark);
        mediaInfo = mainActivity.findViewById(R.id.details_video_mediaInfo);
    }

    private void setData(){
        switch (type) {
            case SevxConsts.ANIMATION:{
                // 获取数据
                VideoAnimationBean animationBean = (VideoAnimationBean) bundle.getSerializable(SevxConsts.ANIMATION_BEAN);

                // 适配页面
                Glide.with(mainActivity).load(animationBean.getLogo()).into(mediaInfo);
                title.setText(String.format("名称：%s", animationBean.getAnimation_name()));
                make.setText(String.format("制作：%s", animationBean.getMake()));
                director.setText(String.format("导演：%s", animationBean.getDirector()));
                screenWriter.setText(String.format("编辑：%s", animationBean.getScreenwriter()));
                series.setText(String.format("系列：%s", animationBean.getSeriesflag()));
                amount.setText(String.format("集数：%s", animationBean.getAmount()));
                container.setText(String.format("容器格式：%s", animationBean.getContainer()));
                codeV.setText(String.format("视频编码：%s", animationBean.getCodev()));
                codeA.setText(String.format("音频编码: %s", animationBean.getCodea()));
                localFlag.setText(String.format("是否本地：%s", animationBean.getLocalflag()));
                remoteFlag.setText(String.format("是否远程：%s", animationBean.getRemoteflag()));
                localUrl.setText(String.format("本地地址：%s", animationBean.getLocalurl()));
                remoteUrl.setText(String.format("远程地址：%s", animationBean.getRemoteurl()));
                subType.setText(String.format("字幕类型：%s", animationBean.getSubtype()));
                subTeam.setText(String.format("字幕组：%s", animationBean.getSubteam()));
                lastWatch.setText(String.format("最后观看时间：%s", animationBean.getLastwatch()));
                lastUpdate.setText(String.format("最后更新时间：%s", animationBean.getUpdatetime()));
                remark.setText(String.format("备注：%s", animationBean.getRemark()));
            } break;

            case SevxConsts.FILM:{
                title.setText(type);
            } break;

//            case SevxConsts.TV:{
//                title.setText(type);
//            } break;
        }
    }
}