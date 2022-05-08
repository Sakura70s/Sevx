package top.sakura70s.sevx.adapters.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.sakura70s.sevx.R;


public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.VideoItemHolder> {

    private final List<String> list;

    public VideoItemAdapter() {
        list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(String.valueOf(i));
        }
    }

    // 构造列表
    @NonNull
    @Override
    public VideoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false)
        );
    }

    // 为列表填充数据
    @Override
    public void onBindViewHolder(@NonNull VideoItemHolder holder, int position) {
        holder.bindData(list, position);
    }

    // 获取列表项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VideoItemHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTitle;
        private final TextView textViewMake;

        public VideoItemHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.item_video_title);
            textViewMake = itemView.findViewById(R.id.item_video_make);
        }

        public void bindData(List<String> list, int position) {
            String s = list.get(position);
            textViewTitle.setText(s);
            textViewMake.setText(s);
        }
    }
}
