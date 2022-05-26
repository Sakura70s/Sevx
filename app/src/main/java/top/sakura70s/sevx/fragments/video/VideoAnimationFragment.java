package top.sakura70s.sevx.fragments.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.activitys.EditActivity;
import top.sakura70s.sevx.activitys.SearchActivity;
import top.sakura70s.sevx.activitys.SevxActivity;
import top.sakura70s.sevx.adapters.video.VideoItemAnimationAdapter;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;


public class VideoAnimationFragment extends Fragment implements View.OnClickListener{
    // 实例化 适配器
    private VideoItemAnimationAdapter videoItemAnimationAdapter;

    // 定义数据
    private List<VideoAnimationBean> list;
    private Activity sevxActivity;
    private String uName;
    private String uPassword;
    private SwipeRefreshLayout swipeRefreshLayout;

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            videoItemAnimationAdapter.setData(list, uName, uPassword);
            // 不到万不得已一般不适用这种方法
            videoItemAnimationAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //数据装填
        list = ((SevxActivity)context).getAnimationList("Init");
        sevxActivity = getActivity();
        uName = ((SevxActivity) context).getUName();
        uPassword = ((SevxActivity) context).getUPassword();

    }


    // Activity onStart时
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_animation, container, false);
    }

    // Activity创建完成时
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView(view);
        this.initSwipeRefresh();
    }

    private void initView(View view){
        FloatingActionButton addButton = sevxActivity.findViewById(R.id.float_button_animation_add);
        FloatingActionButton searchButton = sevxActivity.findViewById(R.id.float_button_animation_search);
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        videoItemAnimationAdapter = new VideoItemAnimationAdapter();
        // 设置数据
        videoItemAnimationAdapter.setData(list, uName, uPassword);
        // 绑定 RecyclerView 控件
        RecyclerView animationRecyclerView = view.findViewById(R.id.recyclerview_video_animation);
        // 1. 设置 layoutManager
        animationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 2. 设置 适配器
        animationRecyclerView.setAdapter(videoItemAnimationAdapter);
    }

    private void initSwipeRefresh(){
        // 下拉刷新视图
        swipeRefreshLayout = sevxActivity.findViewById(R.id.animation_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // 获取 Activity
            SevxActivity sevxActivity = (SevxActivity) getActivity();
            // 通知主Activity刷新数据
            if (sevxActivity != null) {
                sevxActivity.getAnimationList("Refresh");
            }

            Request request = new RequestHelper().getMediaJson(SevxConsts.VIDEO_ANIMATION_GET);
            new Thread(() -> {
                list = new HttpHelper().getAnimationList(request, new OkHttpClient(), new Gson());
                // 刷新成功后发送消息，通知刷新成功
                handler.sendEmptyMessage(0);
            }).start();
        });
    }


    @Override
    public void onClick(View view) {
        // 新增逻辑
        if (view.getId() == R.id.float_button_animation_add) {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            this.setIntentData(intent);
            startActivity(intent);
        }
        // 搜索逻辑
        if (view.getId() == R.id.float_button_animation_search){
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            this.setIntentData(intent);
            startActivity(intent);
        }
    }

    private void setIntentData(Intent intent){
        intent.putExtra(SevxConsts.FROM, SevxConsts.LIST);
        intent.putExtra(SevxConsts.TYPE, SevxConsts.ANIMATION);
        intent.putExtra(SevxConsts.UNAME, uName);
        intent.putExtra(SevxConsts.UPASSWORD, uPassword);
    }
}