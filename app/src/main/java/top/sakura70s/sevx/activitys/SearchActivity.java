package top.sakura70s.sevx.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.adapters.ComicItemAdapter;
import top.sakura70s.sevx.adapters.MusicItemAdapter;
import top.sakura70s.sevx.adapters.NovelItemAdapter;
import top.sakura70s.sevx.adapters.video.VideoItemAnimationAdapter;
import top.sakura70s.sevx.adapters.video.VideoItemFilmAdapter;
import top.sakura70s.sevx.adapters.video.VideoItemSvAdapter;
import top.sakura70s.sevx.adapters.video.VideoItemTvAdapter;
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.beans.sv.VideoSvBean;
import top.sakura70s.sevx.beans.tv.VideoTvBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.helpers.HttpHelper;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private String type;
    private String uName;
    private String uPassword;
    private EditText editText;
    private TextView searchInfo;
    private RecyclerView recyclerView;
    private List<VideoAnimationBean> animationBeanList;
    private List<VideoFilmBean> filmBeanList;
    private List<VideoTvBean> tvBeanList;
    private List<VideoSvBean> svBeanList;
    private List<MusicBean> musicBeanList;
    private List<NovelBean> novelBeanList;
    private List<ComicBean> comicBeanList;

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                // Animation
                case 0:{
                    // 无数据
                    if (animationBeanList == null){
                        searchInfo.setText("A没有查找到任何东西！");
                    }
                    VideoItemAnimationAdapter videoItemAnimationAdapter = new VideoItemAnimationAdapter();
                    videoItemAnimationAdapter.setData(animationBeanList, uName, uPassword);
                    recyclerView.setAdapter(videoItemAnimationAdapter);

                } break;

                // Film
                case 1:{
                    if (filmBeanList == null){
                        searchInfo.setText("F没有查找到任何东西！");
                    }
                    VideoItemFilmAdapter videoItemFilmAdapter = new VideoItemFilmAdapter();
                    videoItemFilmAdapter.setData(filmBeanList, uName, uPassword);
                    recyclerView.setAdapter(videoItemFilmAdapter);
                } break;

                // Tv
                case 2:{
                    if (tvBeanList == null) {
                        searchInfo.setText("T没有查找到任何东西！");
                    }
                    VideoItemTvAdapter videoItemTvAdapter = new VideoItemTvAdapter();
                    videoItemTvAdapter.setData(tvBeanList, uName, uPassword);
                    recyclerView.setAdapter(videoItemTvAdapter);
                } break;

                // Sv
                case 3:{
                    if (svBeanList == null) {
                        searchInfo.setText("S没有查找到任何东西！");
                    }
                    VideoItemSvAdapter videoItemSvAdapter = new VideoItemSvAdapter();
                    videoItemSvAdapter.setData(svBeanList, uName, uPassword);
                    recyclerView.setAdapter(videoItemSvAdapter);
                } break;

                // Music
                case 4:{
                    if (musicBeanList == null){
                        searchInfo.setText("M没有查找到任何东西！");
                    }
                    MusicItemAdapter musicItemAdapter = new MusicItemAdapter();
                    musicItemAdapter.setData(musicBeanList, uName, uPassword);
                    recyclerView.setAdapter(musicItemAdapter);
                } break;

                // Novel
                case 5:{
                    if (novelBeanList == null){
                        searchInfo.setText("N没有查找到任何东西！");
                    }
                    NovelItemAdapter novelItemAdapter = new NovelItemAdapter();
                    novelItemAdapter.setData(novelBeanList, uName, uPassword);
                    recyclerView.setAdapter(novelItemAdapter);
                } break;

                // Comic
                case 6:{
                    if (comicBeanList == null){
                        searchInfo.setText("C没有查找到任何东西！");
                    }
                    ComicItemAdapter comicItemAdapter = new ComicItemAdapter();
                    comicItemAdapter.setData(comicBeanList, uName, uPassword);
                    recyclerView.setAdapter(comicItemAdapter);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initData();
        this.initView();
        // 提示
        Toast.makeText(this, String.format("%s -- %s -- %s",type, uName, uPassword), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        searchInfo = findViewById(R.id.search_info);
        recyclerView = findViewById(R.id.search_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        editText = findViewById(R.id.search_editText);
        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra(SevxConsts.TYPE);
            uName = intent.getStringExtra(SevxConsts.UNAME);
            uPassword = intent.getStringExtra(SevxConsts.UPASSWORD);
        }
    }

    @Override
    public void onClick(View view) {
        // 初始化内容
        String searchName = String.valueOf(editText.getText());
        searchInfo.setText("");


        // 当输入值为空的时候不发出请求
        if (searchName.equals("")){
            Toast.makeText(this, "请输入东西哦", Toast.LENGTH_SHORT).show();
        } else {
            // 输入值不为空的时候开始搜索
            switch (type) {
                case SevxConsts.ANIMATION:{
                    new Thread(() -> {
                        animationBeanList = new HttpHelper().getAnimationListForName(searchName);
                        handler.sendEmptyMessage(0);
                    }).start();
                } break;
                case SevxConsts.FILM:{
                    new Thread(() -> {
                        filmBeanList = new HttpHelper().getFilmListForName(searchName);
                        handler.sendEmptyMessage(1);
                    }).start();
                } break;
                case SevxConsts.TV:{
                    new Thread(() -> {
                        tvBeanList = new HttpHelper().getTvListForName(searchName);
                        handler.sendEmptyMessage(2);
                    }).start();
                } break;
                case  SevxConsts.SV:{
                    new Thread(() -> {
                        svBeanList = new HttpHelper().getSvListForName(searchName);
                        handler.sendEmptyMessage(3);
                    }).start();
                } break;
                case SevxConsts.MUSIC:{
                    new Thread(() -> {
                        musicBeanList = new HttpHelper().getMusicListForName(searchName);
                        handler.sendEmptyMessage(4);
                    }).start();
                } break;
                case SevxConsts.NOVEL:{
                    new Thread(() -> {
                        novelBeanList = new HttpHelper().getNovelListForName(searchName);
                        handler.sendEmptyMessage(5);
                    }).start();
                } break;
                case SevxConsts.COMIC:{
                    new Thread(() -> {
                        comicBeanList = new HttpHelper().getComicListForName(searchName);
                        handler.sendEmptyMessage(6);
                    }).start();
                } break;
            }
        }

    }
}