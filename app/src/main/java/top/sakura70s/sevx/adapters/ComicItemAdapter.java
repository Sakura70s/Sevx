package top.sakura70s.sevx.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.beans.ComicBean;

public class ComicItemAdapter extends RecyclerView.Adapter<ComicItemAdapter.ComicItemHolder> {
    private List<ComicBean> list;
    private String uName;
    private String uPassword;

    @NonNull
    @Override
    public ComicItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ComicItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ComicItemHolder holder, int position) {
        holder.bindData(list.get(position));
        Integer id = list.get(position).getId();
        holder.itemView.setOnClickListener(view -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(SevxConsts.TYPE, SevxConsts.COMIC);
            intent.putExtra(SevxConsts.ID, id);
            intent.putExtra(SevxConsts.UNAME, uName);
            intent.putExtra(SevxConsts.UPASSWORD, uPassword);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<ComicBean> list, String uName, String uPassword) {
        this.list = list;
        this.uName = uName;
        this.uPassword = uPassword;
        notifyItemChanged(list.size());
    }

    public static class ComicItemHolder extends RecyclerView.ViewHolder {

        private final TextView comic_title;
        private final TextView comic_info;
        private final ImageView comic_img;

        public ComicItemHolder(@NonNull View itemView) {
            super(itemView);
            comic_title = itemView.findViewById(R.id.item_book_title);
            comic_info = itemView.findViewById(R.id.item_book_info);
            comic_img = itemView.findViewById(R.id.item_book_img);
        }

        public void bindData(ComicBean comicBean) {
            comic_title.setText(comicBean.getComic_name());
            comic_info.setText(String.format("%s - %s", comicBean.getAuthor(), comicBean.getComic_status()));
            Glide.with(itemView).load(comicBean.getLogo()).into(comic_img);
        }
    }
}
