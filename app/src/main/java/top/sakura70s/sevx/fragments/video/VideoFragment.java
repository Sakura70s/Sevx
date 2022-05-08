package top.sakura70s.sevx.fragments.video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.adapters.video.VideoFragmentAdapter;

public class VideoFragment extends Fragment {
    // 声明全局变量
    private ViewPager viewPager;
    private final List<String> tabTextList = new ArrayList<>();
    private TabLayout tabLayout;
    @Override
    // Activity 创建是回调
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        //实例化 viewPager 以及 tabLayout
        viewPager = view.findViewById(R.id.videoViewPager);
        tabLayout = view.findViewById(R.id.videoTabLayout);
        return view;
    }

    // Activity 启动后回调
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置 tabLayoutList 的内容
        tabLayout.setupWithViewPager(viewPager);
        tabTextList.add("Animation");
        tabTextList.add("Film");
        tabTextList.add("Tv");
        tabTextList.add("Sv");

        // 添加 Fragment list
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new VideoAnimationFragment());
        fragmentList.add(new VideoFilmFragment());
        fragmentList.add(new VideoTvFragment());
        fragmentList.add(new VideoSvFragment());

        // 实例化一个适配器
        VideoFragmentAdapter videoFragmentAdapter = new VideoFragmentAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT, fragmentList);
        // 关联 tabLayout 与 适配器
        viewPager.setAdapter(videoFragmentAdapter);

        // 填充 tabLayout 文本
        int tabCount =tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            tabLayout.getTabAt(i).setText(tabTextList.get(i));
        }
    }
}