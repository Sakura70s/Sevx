package top.sakura70s.sevx.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.beans.VideoFilmBean;
import top.sakura70s.sevx.beans.VideoSvBean;
import top.sakura70s.sevx.beans.VideoTvBean;
import top.sakura70s.sevx.databinding.ActivitySevxBinding;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;

public class SevxActivity extends AppCompatActivity {
    HttpHelper httpHelper = new HttpHelper();
    RequestHelper requestHelper = new RequestHelper();
    OkHttpClient okHttpClient = new OkHttpClient();
    Gson gson = new Gson();

    private String uName;
    private String uPassword;

    // 构建 Request
    Request animationRequest = requestHelper.getMediaJson(SevxConsts.VIDEO_ANIMATION_GET);
    Request filmRequest = requestHelper.getMediaJson(SevxConsts.VIDEO_FILM_GET);
    Request svRequest = requestHelper.getMediaJson(SevxConsts.VIDEO_SV_GET);
    Request tvRequest = requestHelper.getMediaJson(SevxConsts.VIDEO_TV_GET);
    Request musicRequest = requestHelper.getMediaJson(SevxConsts.MUSIC_GET);
    Request novelRequest = requestHelper.getMediaJson(SevxConsts.NOVEL_GET);
    Request comicRequest = requestHelper.getMediaJson(SevxConsts.COMIC_GET);

    // 构建请求结果集合
    public List<VideoAnimationBean> animationBeanList;
    public List<VideoFilmBean> filmBeanList;
    public List<VideoSvBean> svBeanList;
    public List<VideoTvBean> tvBeanList;
    public List<MusicBean> musicBeanList;
    public List<NovelBean> novelBeanList;
    public List<ComicBean> comicBeanList;

    // 接收消息
    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // 当数据加载完成以后
            if (msg.what == 0) {
                // 绑定布局文件
                top.sakura70s.sevx.databinding.ActivitySevxBinding binding = ActivitySevxBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

                AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.navigation_video, R.id.navigation_music, R.id.navigation_novel, R.id.navigation_comic)
                        .build();
                NavController navController = Navigation.findNavController(SevxActivity.this, R.id.nav_host_fragment_activity_sevx);
                NavigationUI.setupActionBarWithNavController(SevxActivity.this, navController, appBarConfiguration);
                NavigationUI.setupWithNavController(binding.navView, navController);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏 Title
        // this.getSupportActionBar().hide();

        // 初始化数据
        this.initData();
    }

    /**
     * 初始化网络数据
     */
    private void initData(){
        Intent intent = getIntent();
        uName = intent.getStringExtra(SevxConsts.UNAME);
        uPassword = intent.getStringExtra(SevxConsts.UPASSWORD);
        // 初始化内容
        new Thread(() -> {
            animationBeanList = httpHelper.getAnimationList(animationRequest, okHttpClient, gson);
            filmBeanList = httpHelper.getFilmList(filmRequest, okHttpClient, gson);
            tvBeanList = httpHelper.getTvList(tvRequest, okHttpClient, gson);
            svBeanList = httpHelper.getSvList(svRequest, okHttpClient, gson);
            musicBeanList = httpHelper.getMusicList(musicRequest, okHttpClient, gson);
            novelBeanList = httpHelper.getNovelList(novelRequest, okHttpClient, gson);
            comicBeanList = httpHelper.getComicList(comicRequest, okHttpClient,gson);

            // 加载数据完成后发送消息
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        }).start();
    }


    /**
     * 定义给子组件调用
     */
    public List<VideoAnimationBean> getAnimationList(String Type){
        switch (Type) {
            case "Init": return animationBeanList;
            case "Refresh": new Thread(() -> this.animationBeanList = httpHelper.getAnimationList(animationRequest, okHttpClient, gson)).start();
            default: return null;
        }
    }
    public List<VideoFilmBean> getFilmList(String Type){
        switch (Type) {
            case "Init": return filmBeanList;
            case "Refresh": {
                new Thread(() -> this.filmBeanList = httpHelper.getFilmList(filmRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public List<VideoSvBean> getSvList(String Type){
        switch (Type) {
            case "Init": return svBeanList;
            case "Refresh": {
                new Thread(() -> this.svBeanList = httpHelper.getSvList(svRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public List<VideoTvBean> getTvList(String Type){
        switch (Type) {
            case "Init": return tvBeanList;
            case "Refresh": {
                new Thread(() -> this.tvBeanList = httpHelper.getTvList(tvRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public List<MusicBean> getMusicList(String Type){
        switch (Type) {
            case "Init": return musicBeanList;
            case "Refresh": {
                new Thread(() -> this.musicBeanList = httpHelper.getMusicList(musicRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public List<NovelBean> getNovelList(String Type){
        switch (Type) {
            case "Init": return novelBeanList;
            case "Refresh": {
                new Thread(() -> this.novelBeanList = httpHelper.getNovelList(novelRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public List<ComicBean> getComicList(String Type){
        switch (Type) {
            case "Init": return comicBeanList;
            case "Refresh": {
                new Thread(() -> this.comicBeanList = httpHelper.getComicList(comicRequest, okHttpClient, gson)).start();
            }
            default: return null;
        }
    }
    public String getUName(){
        return uName;
    }
    public String getUPassword(){
        return uPassword;
    }

}