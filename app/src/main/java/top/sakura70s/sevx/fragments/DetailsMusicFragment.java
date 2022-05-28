package top.sakura70s.sevx.fragments;

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
import top.sakura70s.sevx.beans.MusicBean;

public class DetailsMusicFragment extends Fragment {
    private Activity mainActivity;

    private MusicBean musicBean;

    // 布局
    private ImageView logo;
    private TextView name;
    private TextView album;
    private TextView artist;
    private TextView lyrics;
    private TextView written;
    private TextView lyricType;
    private TextView localFlag;
    private TextView remoteFlag;
    private TextView localUrl;
    private TextView remoteUrl;
    private TextView container;
    private TextView updateTime;
    private TextView remark;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initData();
        this.initView();
        this.setData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            musicBean = (MusicBean) bundle.getSerializable(SevxConsts.MUSIC_BEAN);
        }
    }

    private void initView() {
        logo = mainActivity.findViewById(R.id.details_music_logo);
        name = mainActivity.findViewById(R.id.details_music_name);
        album = mainActivity.findViewById(R.id.details_music_album);
        artist = mainActivity.findViewById(R.id.details_music_artist);
        lyrics = mainActivity.findViewById(R.id.details_music_lyrics);
        written = mainActivity.findViewById(R.id.details_music_written);
        lyricType = mainActivity.findViewById(R.id.details_music_lyric_type);
        localFlag = mainActivity.findViewById(R.id.details_music_local_flag);
        remoteFlag = mainActivity.findViewById(R.id.details_music_remote_flag);
        localUrl = mainActivity.findViewById(R.id.details_music_local_url);
        remoteUrl = mainActivity.findViewById(R.id.details_music_remote_url);
        container = mainActivity.findViewById(R.id.details_music_container);
        updateTime = mainActivity.findViewById(R.id.details_music_update_time);
        remark = mainActivity.findViewById(R.id.details_music_remark);
    }

    private void setData() {
        Glide.with(mainActivity).load(musicBean.getLogo()).into(logo);
        name.setText(String.format("歌曲：%s", musicBean.getMusic_name()));
        album.setText(String.format("专辑：%s", musicBean.getAlbum()));
        artist.setText(String.format("艺术家：%s", musicBean.getArtist()));
        lyrics.setText(String.format("作词：%s", musicBean.getLyrics()));
        written.setText(String.format("作曲：%s", musicBean.getWritten()));
        lyricType.setText(String.format("歌词类型：%s", musicBean.getLyrictype()));
        localFlag.setText(String.format("是否本地：%s", musicBean.getLocalflag()));
        remoteFlag.setText(String.format("是否远程：%s", musicBean.getRemoteflag()));
        localUrl.setText(String.format("本地地址：%s", musicBean.getLocalurl()));
        remoteUrl.setText(String.format("远程地址：%s", musicBean.getRemoteurl()));
        container.setText(String.format("容器格式：%s", musicBean.getContainer()));
        updateTime.setText(String.format("更新时间：%s", musicBean.getUpdatetime()));
        remark.setText(String.format("备注：%s", musicBean.getRemark()));
    }
}