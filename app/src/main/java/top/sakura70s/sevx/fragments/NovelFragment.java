package top.sakura70s.sevx.fragments;

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
import top.sakura70s.sevx.adapters.NovelItemAdapter;
import top.sakura70s.sevx.beans.NovelBean;
import top.sakura70s.sevx.helpers.HttpHelper;


public class NovelFragment extends Fragment {
    private NovelItemAdapter novelItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        novelItemAdapter = new NovelItemAdapter();

        // 获取数据，传递给适配器
        new Thread(() -> {
            try {
                Request request = new HttpHelper().getMediaJson(SevxConsts.NOVEL_GET);
                Response response = new OkHttpClient().newCall(request).execute();
                ResponseBody responseBody = response.body();

                String novelJsonString = responseBody.string();
                NovelBean[] novelBeans = new Gson().fromJson(novelJsonString, NovelBean[].class);

                List<NovelBean> list = Arrays.asList(novelBeans);

                getActivity().runOnUiThread(() -> novelItemAdapter.setData(list));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // 设置适配器
        RecyclerView novelRecyclerView = view.findViewById(R.id.recyclerview_novel);

        novelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        novelRecyclerView.setAdapter(novelItemAdapter);
    }
}