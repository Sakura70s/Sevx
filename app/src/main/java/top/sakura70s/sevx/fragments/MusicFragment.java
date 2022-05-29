package top.sakura70s.sevx.fragments;

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
import top.sakura70s.sevx.adapters.MusicItemAdapter;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.helpers.HttpHelper;
import top.sakura70s.sevx.helpers.RequestHelper;


public class MusicFragment extends Fragment implements View.OnClickListener {
    private MusicItemAdapter musicItemAdapter;
    private List<MusicBean> list;
    Activity sevxActivity;
    private String uName;
    private String uPassword;

    private final Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            musicItemAdapter.setData(list, uName, uPassword);
            musicItemAdapter.notifyItemChanged(list.size());
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        list = ((SevxActivity)context).getMusicList("Init");
        sevxActivity = getActivity();
        uName = ((SevxActivity) context).getUName();
        uPassword = ((SevxActivity) context).getUPassword();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView(view);
        this.initSwipeRefresh();
    }

    private void initView(View view){
        musicItemAdapter = new MusicItemAdapter();
        musicItemAdapter.setData(list, uName, uPassword);
        RecyclerView musicRecyclerView = view.findViewById(R.id.recyclerview_music);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicRecyclerView.setAdapter(musicItemAdapter);

        FloatingActionButton addButton = sevxActivity.findViewById(R.id.float_button_music_add);
        FloatingActionButton searchButton = sevxActivity.findViewById(R.id.float_button_music_search);
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }
    private void initSwipeRefresh(){
        swipeRefreshLayout = sevxActivity.findViewById(R.id.music_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            SevxActivity sevxActivity = (SevxActivity) getActivity();
            assert sevxActivity != null;
            sevxActivity.getMusicList("Refresh");

            Request request = new RequestHelper().getMediaJson(SevxConsts.MUSIC_GET);
            new Thread(() -> {
                list = new HttpHelper().getMusicList(request, new OkHttpClient(), new Gson());

                handler.sendEmptyMessage(0);
            }).start();
        });
    }

    @Override
    public void onClick(View view) {
        // Music 添加按钮 逻辑
        if (view.getId() == R.id.float_button_music_add){
            Intent intent = new Intent(getActivity(), EditActivity.class);
            this.setIntentData(intent);
            startActivity(intent);
        }
        // Music 搜索按钮 逻辑
        if (view.getId() == R.id.float_button_music_search){
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            this.setIntentData(intent);
            startActivity(intent);
        }
    }

    private void setIntentData(Intent intent) {
        intent.putExtra(SevxConsts.FROM, SevxConsts.LIST);
        intent.putExtra(SevxConsts.TYPE, SevxConsts.MUSIC);
        intent.putExtra(SevxConsts.UNAME, uName);
        intent.putExtra(SevxConsts.UPASSWORD, uPassword);
    }
}