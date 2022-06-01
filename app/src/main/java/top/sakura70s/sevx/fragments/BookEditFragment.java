package top.sakura70s.sevx.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.comic.ComicAddBean;
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.comic.ComicUpdateBean;
import top.sakura70s.sevx.beans.novel.NovelAddBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.novel.NovelUpdateBean;
import top.sakura70s.sevx.helpers.HttpHelper;


public class BookEditFragment extends Fragment implements View.OnClickListener {
    private Activity editActivity;
    private final HttpHelper httpHelper;

    private Integer id;
    private String type;
    private String from;
    private String uName;
    private String uPassword;
    private Bundle bundle;

    private EditText seriesEdit;
    private EditText nameEdit;
    private EditText yearEdit;
    private EditText statusEdit;
    private EditText logoEdit;
    private EditText authorEdit;
    private EditText localFlagEdit;
    private EditText localUrlEdit;
    private EditText remoteFlagEdit;
    private EditText remoteUrlEdit;
    private EditText containerEdit;
    private EditText remarkEdit;


    public BookEditFragment() {
        // Required empty public constructor
        httpHelper = new HttpHelper();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initData();

    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            this.from = bundle.getString(SevxConsts.FROM);
            this.type = bundle.getString(SevxConsts.TYPE);
            this.uName = bundle.getString(SevxConsts.UNAME);
            this.uPassword = bundle.getString(SevxConsts.UPASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView();
    }

    private void initView() {
        seriesEdit = editActivity.findViewById(R.id.editPage_book_series_edit);
        nameEdit = editActivity.findViewById(R.id.editPage_book_name_edit);
        yearEdit = editActivity.findViewById(R.id.editPage_book_year_edit);
        statusEdit = editActivity.findViewById(R.id.editPage_book_status_edit);
        logoEdit = editActivity.findViewById(R.id.editPage_book_logo_edit);
        authorEdit = editActivity.findViewById(R.id.editPage_book_author_edit);
        localFlagEdit = editActivity.findViewById(R.id.editPage_book_localFlag_edit);
        localUrlEdit = editActivity.findViewById(R.id.editPage_book_localUrl_edit);
        remoteFlagEdit = editActivity.findViewById(R.id.editPage_book_remoteFlag_edit);
        remoteUrlEdit = editActivity.findViewById(R.id.editPage_book_remoteUrl_edit);
        containerEdit = editActivity.findViewById(R.id.editPage_book_container_edit);
        remarkEdit = editActivity.findViewById(R.id.editPage_book_remark_edit);
        FloatingActionButton floatingActionButton = editActivity.findViewById(R.id.float_button_editPage_book_complete);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (from.equals(SevxConsts.DETAILS)){
            this.setData();
        }
    }

    private void setData() {
        switch (type){
            case SevxConsts.NOVEL:{
                NovelBean novelBean = (NovelBean) bundle.getSerializable(SevxConsts.NOVEL_BEAN);
                this.id = novelBean.getId();
                seriesEdit.setText(String.valueOf(novelBean.getSeriesflag()));
                nameEdit.setText(novelBean.getNovel_name());
                yearEdit.setText(novelBean.getNovel_year());
                statusEdit.setText(novelBean.getNovel_status());
                logoEdit.setText(novelBean.getLogo());
                authorEdit.setText(novelBean.getAuthor());
                localFlagEdit.setText(novelBean.getLocalflag().toString());
                localUrlEdit.setText(novelBean.getLocalurl());
                remoteFlagEdit.setText(novelBean.getRemoteflag().toString());
                remoteUrlEdit.setText(novelBean.getRemoteurl());
                containerEdit.setText(novelBean.getContainer());
                remarkEdit.setText(novelBean.getRemark());
            } break;
            
            case SevxConsts.COMIC:{
                ComicBean comicBean = (ComicBean) bundle.getSerializable(SevxConsts.COMIC_BEAN);
                this.id = comicBean.getId();
                seriesEdit.setText(String.valueOf(comicBean.getSeriesflag()));
                nameEdit.setText(comicBean.getComic_name());
                yearEdit.setText(comicBean.getComic_year());
                statusEdit.setText(comicBean.getComic_status());
                logoEdit.setText(comicBean.getLogo());
                authorEdit.setText(comicBean.getAuthor());
                localFlagEdit.setText(comicBean.getLocalflag().toString());
                localUrlEdit.setText(comicBean.getLocalurl());
                remoteFlagEdit.setText(comicBean.getRemoteflag().toString());
                remoteUrlEdit.setText(comicBean.getRemoteurl());
                containerEdit.setText(comicBean.getContainer());
                remarkEdit.setText(comicBean.getRemark());
            } break;
            
        }
    }

    private String getNovelAddJson(){
        NovelAddBean bean = new NovelAddBean();
        try {
            bean.setSeriesflag(Boolean.valueOf(seriesEdit.getText().toString()));
            bean.setSeriesid(0);
            bean.setNovel_name(nameEdit.getText().toString());
            bean.setNovel_year(yearEdit.getText().toString());
            bean.setNovel_status(statusEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAuthor(authorEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignore){
            return null;
        }
        Log.d("Json", new Gson().toJson(bean));
        return new Gson().toJson(bean);
    }

    private String getNovelUpdateJson(){
        NovelUpdateBean bean = new NovelUpdateBean();

        try {
            bean.setId(id);
            bean.setSeriesflag(Boolean.valueOf(seriesEdit.getText().toString()));
            bean.setNovel_name(nameEdit.getText().toString());
            bean.setNovel_year(yearEdit.getText().toString());
            bean.setNovel_status(statusEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAuthor(authorEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignore){
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getComicAddJson(){
        ComicAddBean bean = new ComicAddBean();
        try {
            bean.setSeriesflag(Boolean.valueOf(seriesEdit.getText().toString()));
            bean.setSeriesid(0);
            bean.setComic_name(nameEdit.getText().toString());
            bean.setComic_year(yearEdit.getText().toString());
            bean.setComic_status(statusEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAuthor(authorEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignore){
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getComicUpdateJson(){
        ComicUpdateBean bean = new ComicUpdateBean();
        try {
            bean.setId(id);
            bean.setSeriesflag(Boolean.valueOf(seriesEdit.getText().toString()));
            bean.setComic_name(nameEdit.getText().toString());
            bean.setComic_year(yearEdit.getText().toString());
            bean.setComic_status(statusEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAuthor(authorEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignore){
            return null;
        }
        return new Gson().toJson(bean);
    }

    @Override
    public void onClick(View view) {
        // 新增
        if (from.equals(SevxConsts.LIST)){
            switch (type){
                case SevxConsts.NOVEL:{
                    String addNovelJson = this.getNovelAddJson();
                    if (addNovelJson != null){
                        new Thread(() -> {
                            if (httpHelper.novelAdd(addNovelJson)){
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show());
                                editActivity.finish();
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }
                } break;

                case SevxConsts.COMIC:{
                    String addComicJson = this.getComicAddJson();
                    if (addComicJson != null){
                        new Thread(() -> {
                            if (httpHelper.comicAdd(addComicJson)){
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show());
                                editActivity.finish();
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }
                } break;
            }
        }


        // 修改
        if (from.equals(SevxConsts.DETAILS)){
            switch (type){
                case SevxConsts.NOVEL:{
                    String updateNovelJson = this.getNovelUpdateJson();
                    if (updateNovelJson != null){
                        new Thread(() -> {
                            if (httpHelper.novelUpdate(updateNovelJson)){
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }
                } break;

                case SevxConsts.COMIC:{
                    String updateComicJson = this.getComicUpdateJson();
                    if (updateComicJson != null){
                        new Thread(() -> {
                            if (httpHelper.comicUpdate(updateComicJson)){
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }
                } break;
            }
        }
    }
}