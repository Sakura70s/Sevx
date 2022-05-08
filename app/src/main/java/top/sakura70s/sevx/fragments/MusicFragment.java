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
import top.sakura70s.sevx.adapters.MusicItemAdapter;
import top.sakura70s.sevx.beans.MusicBean;
import top.sakura70s.sevx.helpers.HttpHelper;


public class MusicFragment extends Fragment {
    private MusicItemAdapter musicItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        musicItemAdapter = new MusicItemAdapter();

        // 获取数据，传递给适配器
        new Thread(() -> {
            try {
                Request request = new HttpHelper().getMediaJson(SevxConsts.MUSIC_GET);
                Response response = new OkHttpClient().newCall(request).execute();
                ResponseBody responseBody = response.body();

                String musicJsonString = responseBody.string();
                MusicBean[] musicBeans = new Gson().fromJson(musicJsonString, MusicBean[].class);

                List<MusicBean> list = Arrays.asList(musicBeans);

                getActivity().runOnUiThread(() -> musicItemAdapter.setData(list));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // 设置适配器
        RecyclerView musicRecyclerView = view.findViewById(R.id.recyclerview_music);

        musicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        musicRecyclerView.setAdapter(musicItemAdapter);
    }
}