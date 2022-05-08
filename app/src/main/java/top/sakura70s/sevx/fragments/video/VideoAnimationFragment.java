package top.sakura70s.sevx.fragments.video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.adapters.video.VideoItemAnimationAdapter;
import top.sakura70s.sevx.beans.VideoAnimationBean;
import top.sakura70s.sevx.helpers.HttpHelper;

public class VideoAnimationFragment extends Fragment{
    // 实例化 适配器
    private VideoItemAnimationAdapter videoItemAnimationAdapter;

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
        videoItemAnimationAdapter = new VideoItemAnimationAdapter();

        // 同步 请求接口
        new Thread(() -> {
            try {
                // 构建请求
                Request request = new HttpHelper().getMediaJson(SevxConsts.VIDEO_ANIMATION_GET);

                // 执行，拿到数据
                Response response = new OkHttpClient().newCall(request).execute();
                ResponseBody responseBody =response.body();

                // 拿到 JSON 字符串
                String Animation_Json = responseBody.string();

                // 解析
                VideoAnimationBean[] videoAnimationBeans = new Gson().fromJson(Animation_Json, VideoAnimationBean[].class);

                // 构建结果集合
                List<VideoAnimationBean> list = Arrays.asList(videoAnimationBeans);

                // 在主线程将数据传递给 适配器
                getActivity().runOnUiThread(() -> videoItemAnimationAdapter.setData(list));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();



        // 绑定 RecyclerView 控件
        RecyclerView animationRecyclerView = view.findViewById(R.id.recyclerview_video_animation);

        // 1. 设置 layoutManager
        animationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 2. 设置 适配器
        animationRecyclerView.setAdapter(videoItemAnimationAdapter);

    }

}