package top.sakura70s.sevx.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.beans.sv.VideoSvBean;
import top.sakura70s.sevx.beans.tv.VideoTvBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.fragments.DetailsBookFragment;
import top.sakura70s.sevx.fragments.DetailsMusicFragment;
import top.sakura70s.sevx.fragments.video.DetailsVideoFragment;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;
import top.sakura70s.sevx.helpers.JsonFrom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoAnimationBean animationBean;
    private MusicBean musicBean;
    private NovelBean novelBean;
    private ComicBean comicBean;
    private VideoFilmBean filmBean;
    private VideoTvBean tvBean;
    private VideoSvBean svBean;
    private Integer id;
    private String type;
    private String uName;
    private String uPassword;

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            MainActivity.this.startFragment();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.initData();
    }

    /**
     * ???????????????
     */
    private void initView(){
        Intent intent = getIntent();
        uName = intent.getStringExtra(SevxConsts.UNAME);
        uPassword = intent.getStringExtra(SevxConsts.UPASSWORD);
        id = intent.getIntExtra(SevxConsts.ID, 0);
        type = intent.getStringExtra(SevxConsts.TYPE);
        FloatingActionButton deleteButton = findViewById(R.id.float_button_main_delete);
        FloatingActionButton editButton = findViewById(R.id.float_button_main_edit);
        deleteButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    /**
     * ??????????????? Id ?????? Type ?????????????????????
     */
    private void initData() {
        switch (type) {
            case SevxConsts.ANIMATION: {
                new Thread(() -> {
                    animationBean = new HttpHelper().getAnimationById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.FILM: {
                new Thread(() -> {
                    filmBean = new HttpHelper().getFilmById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.TV:{
                new Thread(() -> {
                    tvBean = new HttpHelper().getTvById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.SV:{
                new Thread(() -> {
                    svBean = new HttpHelper().getSvById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.MUSIC:{
                new Thread(() -> {
                    musicBean = new HttpHelper().getMusicById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.NOVEL:{
                new Thread(() -> {
                    novelBean = new HttpHelper().getNovelById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.COMIC:{
                new Thread(() -> {
                    comicBean = new HttpHelper().getComicById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;
        }
    }

    /**
     * ????????????????????? Fragment
     */
    private void startFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // Video
        if (type.equals(SevxConsts.ANIMATION) || type.equals(SevxConsts.FILM) || type.equals(SevxConsts.TV) || type.equals(SevxConsts.SV)) {
            DetailsVideoFragment detailsVideoFragment = new DetailsVideoFragment();
            detailsVideoFragment.setArguments(this.getBundle());

            fragmentTransaction.add(R.id.main_view, detailsVideoFragment);
            fragmentTransaction.commit();
        }
        // Music
        if (type.equals(SevxConsts.MUSIC)) {
            DetailsMusicFragment detailsMusicFragment = new DetailsMusicFragment();
            detailsMusicFragment.setArguments(this.getBundle());

            fragmentTransaction.add(R.id.main_view, detailsMusicFragment);
            fragmentTransaction.commit();

        }
        // Book
        if (type.equals(SevxConsts.NOVEL) || type.equals(SevxConsts.COMIC)) {
            DetailsBookFragment detailsBookFragment = new DetailsBookFragment();
            detailsBookFragment.setArguments(this.getBundle());

            fragmentTransaction.add(R.id.main_view, detailsBookFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onClick(View view) {
        // ????????????
        if (view.getId() == R.id.float_button_main_delete) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_delete)
                    .setTitle("??????")
                    .setMessage("?????????????????????")
                    .setNegativeButton("??????", (dialogInterface, i) -> dialogInterface.dismiss())
                    .setPositiveButton("??????", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        deleteItem();
                    }).create();
            // ?????????????????????
            dialog.show();
        } else if (view.getId() == R.id.float_button_main_edit) {
            this.editItem();
        }
    }

    /**
     * ???????????????????????????
     * ?????? EditActivity ???????????????
     */
    private void editItem() {
        Intent intent = new Intent(this, EditActivity.class);
        this.setIntentData(intent);
        // ????????????
        switch (type){
            case SevxConsts.ANIMATION:{
                intent.putExtra(SevxConsts.ANIMATION_BEAN, animationBean);
                startActivity(intent);
            } break;
            case SevxConsts.FILM:{
                intent.putExtra(SevxConsts.FILM_BEAN, filmBean);
                startActivity(intent);
            } break;
            case SevxConsts.TV:{
                intent.putExtra(SevxConsts.TV_BEAN, tvBean);
                startActivity(intent);
            } break;
            case SevxConsts.SV:{
                intent.putExtra(SevxConsts.SV_BEAN, svBean);
                startActivity(intent);
            } break;
            case SevxConsts.MUSIC:{
                intent.putExtra(SevxConsts.MUSIC_BEAN, musicBean);
                startActivity(intent);
            } break;
            case SevxConsts.NOVEL:{
                intent.putExtra(SevxConsts.NOVEL_BEAN, novelBean);
                startActivity(intent);
            } break;
            case SevxConsts.COMIC:{
                intent.putExtra(SevxConsts.COMIC_BEAN, comicBean);
                startActivity(intent);
            }
        }
    }


    /**
     * ???????????????????????????
     * ????????????????????????????????????????????????
     */
    public void deleteItem(){
        switch (type){
            case SevxConsts.ANIMATION:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_ANIMATION_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Animation ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Animation ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.FILM:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_FILM_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Film ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Film ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.TV:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_TV_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Tv ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Tv ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.SV:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_SV_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Sv ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Sv ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.MUSIC:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.MUSIC_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Music ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Music ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.NOVEL:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.NOVEL_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Novel ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Novel ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.COMIC:{
                // ???????????????
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.COMIC_DELETE, json);
                // ????????????
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Comic ????????????", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Comic ????????????", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
        }
    }


    /**
     * ??????????????? Fragment ?????????
     * @return Bundle
     */
    private Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString(SevxConsts.TYPE, type);
        bundle.putString(SevxConsts.UNAME, uName);
        bundle.putString(SevxConsts.UPASSWORD, uPassword);

        switch (type) {
            case SevxConsts.ANIMATION:{
                bundle.putSerializable(SevxConsts.ANIMATION_BEAN, animationBean);
                return bundle;
            }

            case SevxConsts.MUSIC:{
                bundle.putSerializable(SevxConsts.MUSIC_BEAN, musicBean);
                return bundle;
            }

            case SevxConsts.NOVEL:{
                bundle.putSerializable(SevxConsts.NOVEL_BEAN, novelBean);
                return bundle;
            }

            case SevxConsts.FILM:{
                bundle.putSerializable(SevxConsts.FILM_BEAN, filmBean);
                return bundle;
            }

            case SevxConsts.TV:{
                bundle.putSerializable(SevxConsts.TV_BEAN, tvBean);
                return bundle;
            }

            case SevxConsts.SV:{
                bundle.putSerializable(SevxConsts.SV_BEAN, svBean);
                return bundle;
            }

            case SevxConsts.COMIC:{
                bundle.putSerializable(SevxConsts.COMIC_BEAN, comicBean);
                return bundle;
            }
        }
        return bundle;
    }

    /**
     * ????????? Activity ???????????????
     * @param intent Intent
     */
    private void setIntentData(Intent intent){
        intent.putExtra(SevxConsts.FROM, SevxConsts.DETAILS);
        intent.putExtra(SevxConsts.TYPE, type);
        intent.putExtra(SevxConsts.UNAME, uName);
        intent.putExtra(SevxConsts.UPASSWORD, uPassword);
        intent.putExtra(SevxConsts.ID, id);
    }
}