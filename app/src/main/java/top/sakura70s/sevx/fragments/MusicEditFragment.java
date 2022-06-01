package top.sakura70s.sevx.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.music.MusicAddBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.music.MusicUpdateBean;
import top.sakura70s.sevx.helpers.HttpHelper;


public class MusicEditFragment extends Fragment implements View.OnClickListener {
    private Activity editActivity;
    private final HttpHelper httpHelper;

    private Integer id;
    private String from;
    private String uName;
    private String uPassword;
    private Bundle bundle;

    private EditText nameEdit;
    private EditText yearEdit;
    private EditText logoEdit;
    private EditText artistEdit;
    private EditText albumEdit;
    private EditText lyricsEdit;
    private EditText writtenEdit;
    private EditText localFlagEdit;
    private EditText localUrlEdit;
    private EditText remoteFlagEdit;
    private EditText remoteUrlEdit;
    private EditText containerEdit;
    private EditText lyricTypeEdit;
    private EditText remarkEdit;


    public MusicEditFragment() {
        // Required empty public constructor
        httpHelper = new HttpHelper();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initData();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            this.from = bundle.getString(SevxConsts.FROM);
            this.uName = bundle.getString(SevxConsts.UNAME);
            this.uPassword = bundle.getString(SevxConsts.UPASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView();
    }

    private void initView() {
        nameEdit = editActivity.findViewById(R.id.editPage_music_name_edit);
        yearEdit = editActivity.findViewById(R.id.editPage_music_year_edit);
        logoEdit = editActivity.findViewById(R.id.editPage_music_logo_edit);
        artistEdit = editActivity.findViewById(R.id.editPage_music_artist_edit);
        albumEdit = editActivity.findViewById(R.id.editPage_music_album_edit);
        lyricsEdit = editActivity.findViewById(R.id.editPage_music_lyrics_edit);
        writtenEdit = editActivity.findViewById(R.id.editPage_music_written_edit);
        localFlagEdit = editActivity.findViewById(R.id.editPage_music_localFlag_edit);
        localUrlEdit = editActivity.findViewById(R.id.editPage_music_localUrl_edit);
        remoteFlagEdit = editActivity.findViewById(R.id.editPage_music_remoteFlag_edit);
        remoteUrlEdit = editActivity.findViewById(R.id.editPage_music_remoteUrl_edit);
        containerEdit = editActivity.findViewById(R.id.editPage_music_container_edit);
        lyricTypeEdit = editActivity.findViewById(R.id.editPage_music_lyricType_edit);
        remarkEdit = editActivity.findViewById(R.id.editPage_music_remark_edit);
        FloatingActionButton floatingActionButton = editActivity.findViewById(R.id.float_button_editPage_music_complete);
        floatingActionButton.setOnClickListener(this);
    }


    private void setData() {
        MusicBean musicBean = (MusicBean) bundle.getSerializable(SevxConsts.MUSIC_BEAN);
        this.id = musicBean.getId();
        this.nameEdit.setText(musicBean.getMusic_name());
        this.yearEdit.setText(String.valueOf(musicBean.getMusic_year()));
        this.logoEdit.setText(String.valueOf(musicBean.getLogo()));
        this.artistEdit.setText(String.valueOf(musicBean.getArtist()));
        this.albumEdit.setText(String.valueOf(musicBean.getAlbum()));
        this.lyricsEdit.setText(String.valueOf(musicBean.getLyrics()));
        this.writtenEdit.setText(String.valueOf(musicBean.getWritten()));
        this.localFlagEdit.setText(String.valueOf(musicBean.getLocalflag()));
        this.localUrlEdit.setText(String.valueOf(musicBean.getLocalurl()));
        this.remoteFlagEdit.setText(String.valueOf(musicBean.getRemoteflag()));
        this.remoteUrlEdit.setText(String.valueOf(musicBean.getRemoteurl()));
        this.containerEdit.setText(String.valueOf(musicBean.getContainer()));
        this.lyricTypeEdit.setText(String.valueOf(musicBean.getLyrictype()));
        this.remarkEdit.setText(String.valueOf(musicBean.getRemark()));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (from.equals(SevxConsts.DETAILS)){
            this.setData();
        }
    }



    @Override
    public void onClick(View view) {
        // 新增
        if (from.equals(SevxConsts.LIST)){
            String addMusicJson = this.getMusicAddJson();
            if (addMusicJson != null){
                new Thread(() -> {
                    if (httpHelper.musicAdd(addMusicJson)){
                        editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show());
                        editActivity.finish();
                    } else {
                        editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加失败！", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
            }
        }

        // 更新
        if (from.equals(SevxConsts.DETAILS)){
            String updateMusicJson = this.getMusicUpdateJson();
            if (updateMusicJson != null){
                new Thread(() -> {
                    if (httpHelper.musicUpdate(updateMusicJson)){
                        editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                        editActivity.finish();
                    } else {
                        editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 获取添加音乐 Json
     * @return jsonString
     */
    private String getMusicAddJson(){
        MusicAddBean musicAddBean = new MusicAddBean();
        try {
            musicAddBean.setMusic_name(nameEdit.getText().toString());
            musicAddBean.setMusic_year(yearEdit.getText().toString());
            musicAddBean.setLogo(logoEdit.getText().toString());
            musicAddBean.setArtist(artistEdit.getText().toString());
            musicAddBean.setAlbum(albumEdit.getText().toString());
            musicAddBean.setLyrics(lyricsEdit.getText().toString());
            musicAddBean.setWritten(writtenEdit.getText().toString());
            musicAddBean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            musicAddBean.setLocalurl(localUrlEdit.getText().toString());
            musicAddBean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            musicAddBean.setRemoteurl(remoteUrlEdit.getText().toString());
            musicAddBean.setContainer(containerEdit.getText().toString());
            musicAddBean.setLyrictype(lyricTypeEdit.getText().toString());
            musicAddBean.setRemark(remarkEdit.getText().toString());
            musicAddBean.setUname(uName);
            musicAddBean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        Log.d("Json", new Gson().toJson(musicAddBean));
        return new Gson().toJson(musicAddBean);
    }

    /**
     * 获取更新音乐 Json
     * @return jsonString
     */
    private String getMusicUpdateJson(){
        MusicUpdateBean musicUpdateBean = new MusicUpdateBean();
        try {
            musicUpdateBean.setId(id);
            musicUpdateBean.setMusic_name(nameEdit.getText().toString());
            musicUpdateBean.setMusic_year(yearEdit.getText().toString());
            musicUpdateBean.setLogo(logoEdit.getText().toString());
            musicUpdateBean.setArtist(artistEdit.getText().toString());
            musicUpdateBean.setAlbum(albumEdit.getText().toString());
            musicUpdateBean.setLyrics(lyricsEdit.getText().toString());
            musicUpdateBean.setWritten(writtenEdit.getText().toString());
            musicUpdateBean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            musicUpdateBean.setLocalurl(localUrlEdit.getText().toString());
            musicUpdateBean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            musicUpdateBean.setRemoteurl(remoteUrlEdit.getText().toString());
            musicUpdateBean.setContainer(containerEdit.getText().toString());
            musicUpdateBean.setLyrictype(lyricTypeEdit.getText().toString());
            musicUpdateBean.setRemark(remarkEdit.getText().toString());
            musicUpdateBean.setUname(uName);
            musicUpdateBean.setUpassword(uPassword);
        } catch (Exception ignored){
            return null;
        }

        return new Gson().toJson(musicUpdateBean);
    }


}