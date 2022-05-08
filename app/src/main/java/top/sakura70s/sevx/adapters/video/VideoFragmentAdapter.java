package top.sakura70s.sevx.adapters.video;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class VideoFragmentAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> list;
    public VideoFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
        super(fm, behavior);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) { return list.get(position); }

    @Override
    public int getCount() { return list.size(); }
}
