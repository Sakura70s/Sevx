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
import top.sakura70s.sevx.activitys.SearchActivity;
import top.sakura70s.sevx.activitys.SevxActivity;
import top.sakura70s.sevx.adapters.video.VideoItemSvAdapter;
import top.sakura70s.sevx.beans.VideoSvBean;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;


public class VideoSvFragment extends Fragment implements View.OnClickListener{
    // 实例化 适配器
    private VideoItemSvAdapter videoItemSvAdapter;
    private List<VideoSvBean> list;
    Activity sevxActivity;
    private String uName;
    private String uPassword;

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            videoItemSvAdapter.setData(list);
            videoItemSvAdapter.notifyItemChanged(list.size());
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        list = ((SevxActivity)context).getSvList("Init");
        sevxActivity = getActivity();
        uName = ((SevxActivity) context).getUName();
        uPassword = ((SevxActivity) context).getUPassword();
    }

    // Activity onStart时
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_sv, container, false);
    }

    // Activity创建完成时
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView(view);
        this.initSwipeRefresh();

    }

    private void initView(View view){
        videoItemSvAdapter = new VideoItemSvAdapter();
        // 设置数据
        videoItemSvAdapter.setData(list);
        // 绑定 RecyclerView 控件
        RecyclerView svRecyclerView = view.findViewById(R.id.recyclerview_video_sv);
        // 1. 设置 layoutManager
        svRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 2. 设置 适配器
        svRecyclerView.setAdapter(videoItemSvAdapter);

        FloatingActionButton addButton = sevxActivity.findViewById(R.id.float_button_sv_add);
        FloatingActionButton searchButton = sevxActivity.findViewById(R.id.float_button_sv_search);
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    private void initSwipeRefresh(){
        swipeRefreshLayout = sevxActivity.findViewById(R.id.sv_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            SevxActivity sevxActivity = (SevxActivity) getActivity();
            if (sevxActivity != null) {
                sevxActivity.getSvList("Refresh");
            }
            Request request = new RequestHelper().getMediaJson(SevxConsts.VIDEO_SV_GET);
            new Thread(() -> {
                list = new HttpHelper().getSvList(request, new OkHttpClient(), new Gson());

                handler.sendEmptyMessage(0);
            }).start();

        });
    }

    @Override
    public void onClick(View view) {
        // Sv 添加按钮 逻辑
        if (view.getId() == R.id.float_button_sv_add){
            Toast.makeText(getContext(), "正在路上。。。", Toast.LENGTH_SHORT).show();
        }
        // Sv 搜索按钮 逻辑
        if (view.getId() == R.id.float_button_sv_search){
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            this.setIntentData(intent);
            startActivity(intent);
        }
    }

    private void setIntentData(Intent intent) {
        intent.putExtra(SevxConsts.FROM, SevxConsts.LIST);
        intent.putExtra(SevxConsts.TYPE, SevxConsts.SV);
        intent.putExtra(SevxConsts.UNAME, uName);
        intent.putExtra(SevxConsts.UPASSWORD, uPassword);
    }
}