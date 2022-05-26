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
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.fragments.video.DetailsVideoFragment;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;
import top.sakura70s.sevx.helpers.JsonFrom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoAnimationBean animationBean;
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
        this.initData();
    }

    private void initData() {
        Intent intent = getIntent();
        uName = intent.getStringExtra(SevxConsts.UNAME);
        uPassword = intent.getStringExtra(SevxConsts.UPASSWORD);
        id = intent.getIntExtra(SevxConsts.ID, 0);
        type = intent.getStringExtra(SevxConsts.TYPE);

        switch (type) {
            case SevxConsts.ANIMATION: {
                new Thread(() -> {
                    HttpHelper httpHelper = new HttpHelper();
                    animationBean = httpHelper.getAnimationById(id);
                    handler.sendEmptyMessage(0);
                }).start();
            } break;

            case SevxConsts.FILM: {

            } break;
        }
    }

    private void startFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 影视
        if (type.equals(SevxConsts.ANIMATION) || type.equals(SevxConsts.FILM) || type.equals(SevxConsts.TV) || type.equals(SevxConsts.SV)) {
            DetailsVideoFragment detailsVideoFragment = new DetailsVideoFragment();

            Bundle bundle = new Bundle();
            this.setBundleData(bundle);
            detailsVideoFragment.setArguments(bundle);

            fragmentTransaction.add(R.id.main_view, detailsVideoFragment);
            fragmentTransaction.commit();
        }
        if (type.equals(SevxConsts.MUSIC)) {

        }
        if (type.equals(SevxConsts.NOVEL) || type.equals(SevxConsts.COMIC)) {

        }

    }

    private void initView(){
        FloatingActionButton deleteButton = findViewById(R.id.float_button_main_delete);
        FloatingActionButton editButton = findViewById(R.id.float_button_main_edit);
        deleteButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 删除按钮
        if (view.getId() == R.id.float_button_main_delete) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_delete)
                    .setTitle("删除")
                    .setMessage("确定要删除吗？")
                    .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        deleteItem();
                    }).create();
            // 显示模态对话框
            dialog.show();
        } else if (view.getId() == R.id.float_button_main_edit) {
            this.editItem();
        }
    }

    /**
     * 详情页编辑按钮逻辑
     */
    private void editItem() {
        // 编辑按钮
        switch (type){
            case SevxConsts.ANIMATION:{
                Intent intent = new Intent(this, EditActivity.class);
                this.setIntentData(intent);
                intent.putExtra(SevxConsts.ANIMATION_BEAN, animationBean);
                startActivity(intent);
            } break;
            case SevxConsts.FILM:{
                Toast.makeText(this, "Film is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.TV:{
                Toast.makeText(this, "Tv is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.SV:{
                Toast.makeText(this, "Sv is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.MUSIC:{
                Toast.makeText(this, "Music is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.NOVEL:{
                Toast.makeText(this, "Novel is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.COMIC:{
                Toast.makeText(this, "Comic is TobeContinued", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 详情页删除按钮逻辑
     */
    public void deleteItem(){
        switch (type){
            case SevxConsts.ANIMATION:{
                // 构建请求体
                JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_ANIMATION_DELETE, json);
                // 执行删除
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show());
                    }
                });
            } break;
            case SevxConsts.FILM:{
                Toast.makeText(this, "Film is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.TV:{
                Toast.makeText(this, "Tv is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.SV:{
                Toast.makeText(this, "Sv is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.MUSIC:{
                Toast.makeText(this, "Music is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.NOVEL:{
                Toast.makeText(this, "Novel is TobeContinued", Toast.LENGTH_SHORT).show();
            } break;
            case SevxConsts.COMIC:{
                Toast.makeText(this, "Comic is TobeContinued", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setBundleData(Bundle bundle){
        bundle.putString(SevxConsts.TYPE, type);
        bundle.putString(SevxConsts.UNAME, uName);
        bundle.putString(SevxConsts.UPASSWORD, uPassword);
    }
    
    private void setIntentData(Intent intent){
        intent.putExtra(SevxConsts.FROM, SevxConsts.DETAILS);
        intent.putExtra(SevxConsts.TYPE, type);
        intent.putExtra(SevxConsts.UNAME, uName);
        intent.putExtra(SevxConsts.UPASSWORD, uPassword);
        intent.putExtra(SevxConsts.ID, id);
    }

    /**
     * 定义给子组件调用
     */
    public VideoAnimationBean getAnimationBean() {
        return animationBean;
    }
}