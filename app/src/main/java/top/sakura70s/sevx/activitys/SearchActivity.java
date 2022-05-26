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
import top.sakura70s.sevx.adapters.video.VideoItemAnimationAdapter;
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

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                // Animation
                case 0:{
                    VideoItemAnimationAdapter videoItemAnimationAdapter = new VideoItemAnimationAdapter();
                    // 未查找到数据
                    if (animationBeanList == null){
                        searchInfo.setText("没有查找到任何东西！");
                    }
                    videoItemAnimationAdapter.setData(animationBeanList, uName, uPassword);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerView.setAdapter(videoItemAnimationAdapter);
                } break;
                // Film
                case 1:{

                }
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
            switch (type) {
                case SevxConsts.ANIMATION:{
                    new Thread(() -> {
                        animationBeanList = new HttpHelper().getAnimationListForName(searchName);
                        handler.sendEmptyMessage(0);
                    }).start();
                } break;
                case SevxConsts.FILM:{
                    Toast.makeText(this, "Film Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
                case SevxConsts.TV:{
                    Toast.makeText(this, "Tv Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
                case  SevxConsts.SV:{
                    Toast.makeText(this, "Sv Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
                case SevxConsts.MUSIC:{
                    Toast.makeText(this, "Music Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
                case SevxConsts.NOVEL:{
                    Toast.makeText(this, "Novel Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
                case SevxConsts.COMIC:{
                    Toast.makeText(this, "Comic Coming Soon", Toast.LENGTH_SHORT).show();
                } break;
            }
        }

    }
}