package top.sakura70s.sevx.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.NovelBean;

public class DetailsBookFragment extends Fragment {
    private Activity mainActivity;
    private String type;

    // 布局
    private ImageView logo;
    private TextView name;
    private TextView author;
    private TextView status;
    private TextView updateTime;
    private TextView series;
    private TextView localFlag;
    private TextView remoteFlag;
    private TextView localUrl;
    private TextView remoteUrl;
    private TextView remark;

    // 传入数据
    private Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initData();
        this.initView();
        this.setData();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null){
            type = bundle.getString(SevxConsts.TYPE);
        }
    }

    private void initView() {
        logo = mainActivity.findViewById(R.id.details_book_logo);
        name = mainActivity.findViewById(R.id.details_book_name);
        author = mainActivity.findViewById(R.id.details_book_author);
        status = mainActivity.findViewById(R.id.details_book_status);
        updateTime = mainActivity.findViewById(R.id.details_book_update_time);
        series = mainActivity.findViewById(R.id.details_book_series);
        localFlag = mainActivity.findViewById(R.id.details_book_local_flag);
        remoteFlag = mainActivity.findViewById(R.id.details_book_remote_flag);
        localUrl = mainActivity.findViewById(R.id.details_book_local_url);
        remoteUrl = mainActivity.findViewById(R.id.details_book_remote_url);
        remark = mainActivity.findViewById(R.id.details_book_remark);
    }

    private void setData() {
        switch (type){
            case SevxConsts.NOVEL:{
                NovelBean novelBean = (NovelBean) bundle.getSerializable(SevxConsts.NOVEL_BEAN);

                // 适配页面
                Glide.with(mainActivity).load(novelBean.getLogo()).into(logo);
                name.setText(String.format("%s",novelBean.getNovel_name()));
                author.setText(String.format("作者：%s",novelBean.getAuthor()));
                status.setText(String.format("连载状态：%s",novelBean.getNovel_status()));
                updateTime.setText(String.format("更新时间：%s",novelBean.getUpdatetime()));
                series.setText(String.format("系列标志：%s",novelBean.getSeriesflag()));
                localFlag.setText(String.format("本地标志：%s",novelBean.getLocalflag()));
                remoteFlag.setText(String.format("远程标志：%s",novelBean.getRemoteflag()));
                localUrl.setText(String.format("本地地址：%s",novelBean.getLocalurl()));
                remoteUrl.setText(String.format("远程地址：%s",novelBean.getRemoteurl()));
                remark.setText(String.format("备注：%s",novelBean.getRemark()));
            } break;

            case SevxConsts.COMIC:{
                name.setText(type);
            }
        }
    }
}