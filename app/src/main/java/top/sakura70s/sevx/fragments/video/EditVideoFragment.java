package top.sakura70s.sevx.fragments.video;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.film.FilmAddBean;
import top.sakura70s.sevx.beans.film.FilmUpdateBean;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.beans.animation.AnimationAddBean;
import top.sakura70s.sevx.beans.animation.AnimationUpdateBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.beans.sv.SvAddBean;
import top.sakura70s.sevx.beans.sv.SvUpdateBean;
import top.sakura70s.sevx.beans.sv.VideoSvBean;
import top.sakura70s.sevx.beans.tv.TvAddBean;
import top.sakura70s.sevx.beans.tv.TvUpdateBean;
import top.sakura70s.sevx.beans.tv.VideoTvBean;
import top.sakura70s.sevx.helpers.HttpHelper;

public class EditVideoFragment extends Fragment implements View.OnClickListener {
    private Activity editActivity;
    private final HttpHelper httpHelper;

    private Integer id;
    private String type;
    private String from;
    private String uName;
    private String uPassword;
    private Bundle bundle;

    private EditText seriesFlagEdit;
    private LinearLayout seriesFlagLayout;
    private EditText seriesIdEdit;
    private LinearLayout seriesIdLayout;
    private EditText nameEdit;
    private EditText yearEdit;
    private EditText directorEdit;
    private LinearLayout directorLayout;
    private EditText screenWriterEdit;
    private LinearLayout screenWriterLayout;
    private EditText makeEdit;
    private EditText logoEdit;
    private EditText amountEdit;
    private LinearLayout amountLayout;
    private EditText localFlagEdit;
    private EditText localUrlEdit;
    private EditText remoteFlagEdit;
    private EditText remoteUrlEdit;
    private EditText containerEdit;
    private EditText codeVEdit;
    private EditText codeAEdit;
    private EditText subTypeEdit;
    private LinearLayout subTypeLayout;
    private EditText subTeamEdit;
    private LinearLayout subTeamLayout;
    private EditText lastWatchEdit;
    private LinearLayout lastWatchLayout;
    private EditText remarkEdit;
    private EditText svType;
    private LinearLayout svTypeLayout;


    public EditVideoFragment() {
        // Required empty public constructor
        httpHelper = new HttpHelper();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    private void initView(){
        // 初始化控件
        seriesFlagEdit = editActivity.findViewById(R.id.editPage_video_seriesFlag_edit);
        seriesFlagLayout = editActivity.findViewById(R.id.editPage_video_seriesFlag);
        seriesIdEdit = editActivity.findViewById(R.id.editPage_video_seriesId_edit);
        seriesIdLayout = editActivity.findViewById(R.id.editPage_video_seriesId);
        nameEdit = editActivity.findViewById(R.id.editPage_video_name_edit);
        yearEdit = editActivity.findViewById(R.id.editPage_video_year_edit);
        directorEdit = editActivity.findViewById(R.id.editPage_video_director_edit);
        directorLayout = editActivity.findViewById(R.id.editPage_video_director);
        screenWriterEdit = editActivity.findViewById(R.id.editPage_video_screenWriter_edit);
        screenWriterLayout = editActivity.findViewById(R.id.editPage_video_screenWriter);
        makeEdit = editActivity.findViewById(R.id.editPage_video_make_edit);
        logoEdit = editActivity.findViewById(R.id.editPage_video_logo_edit);
        amountEdit = editActivity.findViewById(R.id.editPage_video_amount_edit);
        amountLayout = editActivity.findViewById(R.id.editPage_video_amount);
        localFlagEdit = editActivity.findViewById(R.id.editPage_video_localFlag_edit);
        localUrlEdit = editActivity.findViewById(R.id.editPage_video_localUrl_edit);
        remoteFlagEdit = editActivity.findViewById(R.id.editPage_video_remoteFlag_edit);
        remoteUrlEdit = editActivity.findViewById(R.id.editPage_video_remoteUrl_edit);
        containerEdit = editActivity.findViewById(R.id.editPage_video_container_edit);
        codeVEdit = editActivity.findViewById(R.id.editPage_video_codeV_edit);
        codeAEdit = editActivity.findViewById(R.id.editPage_video_codeA_edit);
        subTypeEdit = editActivity.findViewById(R.id.editPage_video_subType_edit);
        subTypeLayout = editActivity.findViewById(R.id.editPage_video_subType);
        subTeamEdit = editActivity.findViewById(R.id.editPage_video_subTeam_edit);
        subTeamLayout = editActivity.findViewById(R.id.editPage_video_subTeam);
        lastWatchEdit = editActivity.findViewById(R.id.editPage_video_lastWatch_edit);
        lastWatchLayout = editActivity.findViewById(R.id.editPage_video_lastWatch);
        remarkEdit = editActivity.findViewById(R.id.editPage_video_remark_edit);
        svType = editActivity.findViewById(R.id.editPage_video_svType_edit);
        svTypeLayout = editActivity.findViewById(R.id.editPage_video_svType);
        FloatingActionButton completeButton = editActivity.findViewById(R.id.float_button_editPage_video_complete);
        completeButton.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // 详情页来的预先填写数据
        if (from.equals(SevxConsts.DETAILS)){
            this.setData();
        }

        // 首页来的根据不同类型设置视图
        if (from.equals(SevxConsts.LIST)){
            this.setView();
        }
    }

    private void setView() {
        switch (type){
            case SevxConsts.ANIMATION:

            case SevxConsts.TV: {
                this.svTypeLayout.setVisibility(View.GONE);
            } break;

            case SevxConsts.FILM:{
                this.amountLayout.setVisibility(View.GONE);
                this.svTypeLayout.setVisibility(View.GONE);
            } break;

            case SevxConsts.SV:{
                this.seriesFlagLayout.setVisibility(View.GONE);
                this.seriesIdLayout.setVisibility(View.GONE);
                this.directorLayout.setVisibility(View.GONE);
                this.screenWriterLayout.setVisibility(View.GONE);
                this.amountLayout.setVisibility(View.GONE);
                this.subTeamLayout.setVisibility(View.GONE);
                this.subTypeLayout.setVisibility(View.GONE);
                this.lastWatchLayout.setVisibility(View.GONE);
            } break;
        }
    }

    private void setData(){
        switch (type){
            case SevxConsts.ANIMATION: {
                VideoAnimationBean animationBean = (VideoAnimationBean) bundle.getSerializable(SevxConsts.ANIMATION_BEAN);
                
                this.id = animationBean.getId();
                this.seriesFlagEdit.setText(animationBean.getSeriesflag().toString());
                this.seriesIdEdit.setText(String.valueOf(animationBean.getSeriesid()));
                this.nameEdit.setText(String.valueOf(animationBean.getAnimation_name()));
                this.yearEdit.setText(String.valueOf(animationBean.getAnimation_year()));
                this.directorEdit.setText(String.valueOf(animationBean.getDirector()));
                this.screenWriterEdit.setText(String.valueOf(animationBean.getScreenwriter()));
                this.makeEdit.setText(String.valueOf(animationBean.getMake()));
                this.logoEdit.setText(String.valueOf(animationBean.getLogo()));
                this.amountEdit.setText(String.valueOf(animationBean.getAmount()));
                this.localFlagEdit.setText(String.valueOf(animationBean.getLocalflag()));
                this.localUrlEdit.setText(String.valueOf(animationBean.getLocalurl()));
                this.remoteFlagEdit.setText(String.valueOf(animationBean.getRemoteflag()));
                this.remoteUrlEdit.setText(String.valueOf(animationBean.getRemoteurl()));
                this.containerEdit.setText(String.valueOf(animationBean.getContainer()));
                this.codeVEdit.setText(String.valueOf(animationBean.getCodev()));
                this.codeAEdit.setText(String.valueOf(animationBean.getCodea()));
                this.subTypeEdit.setText(String.valueOf(animationBean.getSubtype()));
                this.subTeamEdit.setText(String.valueOf(animationBean.getSubteam()));
                this.lastWatchEdit.setText(String.valueOf(animationBean.getLastwatch()));
                this.remarkEdit.setText(String.valueOf(animationBean.getRemark()));
                this.svTypeLayout.setVisibility(View.GONE);
            } break;

            case SevxConsts.FILM:{
                VideoFilmBean filmBean = (VideoFilmBean) bundle.getSerializable(SevxConsts.FILM_BEAN);

                this.id = filmBean.getId();
                this.seriesFlagEdit.setText(filmBean.getSeriesflag().toString());
                this.seriesIdEdit.setText(String.valueOf(filmBean.getSeriesid()));
                this.nameEdit.setText(String.valueOf(filmBean.getFilm_name()));
                this.yearEdit.setText(String.valueOf(filmBean.getFilm_year()));
                this.directorEdit.setText(String.valueOf(filmBean.getDirector()));
                this.screenWriterEdit.setText(String.valueOf(filmBean.getScreenwriter()));
                this.makeEdit.setText(String.valueOf(filmBean.getMake()));
                this.logoEdit.setText(String.valueOf(filmBean.getLogo()));
                this.amountLayout.setVisibility(View.GONE);
                this.localFlagEdit.setText(String.valueOf(filmBean.getLocalflag()));
                this.localUrlEdit.setText(String.valueOf(filmBean.getLocalurl()));
                this.remoteFlagEdit.setText(String.valueOf(filmBean.getRemoteflag()));
                this.remoteUrlEdit.setText(String.valueOf(filmBean.getRemoteurl()));
                this.containerEdit.setText(String.valueOf(filmBean.getContainer()));
                this.codeVEdit.setText(String.valueOf(filmBean.getCodev()));
                this.codeAEdit.setText(String.valueOf(filmBean.getCodea()));
                this.subTypeEdit.setText(String.valueOf(filmBean.getSubtype()));
                this.subTeamEdit.setText(String.valueOf(filmBean.getSubteam()));
                this.lastWatchEdit.setText(String.valueOf(filmBean.getLastwatch()));
                this.remarkEdit.setText(String.valueOf(filmBean.getRemark()));
                this.svTypeLayout.setVisibility(View.GONE);
            } break;
            
            case SevxConsts.TV:{
                VideoTvBean tvBean = (VideoTvBean) bundle.getSerializable(SevxConsts.TV_BEAN);

                this.id = tvBean.getId();
                this.seriesFlagEdit.setText(tvBean.getSeriesflag().toString());
                this.seriesIdEdit.setText(String.valueOf(tvBean.getSeriesid()));
                this.nameEdit.setText(String.valueOf(tvBean.getTv_name()));
                this.yearEdit.setText(String.valueOf(tvBean.getTv_year()));
                this.directorEdit.setText(String.valueOf(tvBean.getDirector()));
                this.screenWriterEdit.setText(String.valueOf(tvBean.getScreenwriter()));
                this.makeEdit.setText(String.valueOf(tvBean.getMake()));
                this.logoEdit.setText(String.valueOf(tvBean.getLogo()));
                this.amountEdit.setText(String.valueOf(tvBean.getAmount()));
                this.localFlagEdit.setText(String.valueOf(tvBean.getLocalflag()));
                this.localUrlEdit.setText(String.valueOf(tvBean.getLocalurl()));
                this.remoteFlagEdit.setText(String.valueOf(tvBean.getRemoteflag()));
                this.remoteUrlEdit.setText(String.valueOf(tvBean.getRemoteurl()));
                this.containerEdit.setText(String.valueOf(tvBean.getContainer()));
                this.codeVEdit.setText(String.valueOf(tvBean.getCodev()));
                this.codeAEdit.setText(String.valueOf(tvBean.getCodea()));
                this.subTypeEdit.setText(String.valueOf(tvBean.getSubtype()));
                this.subTeamEdit.setText(String.valueOf(tvBean.getSubteam()));
                this.lastWatchEdit.setText(String.valueOf(tvBean.getLastwatch()));
                this.remarkEdit.setText(String.valueOf(tvBean.getRemark()));
                this.svTypeLayout.setVisibility(View.GONE);
            } break;
            
            case SevxConsts.SV:{
                VideoSvBean svBean = (VideoSvBean) bundle.getSerializable(SevxConsts.SV_BEAN);

                this.id = svBean.getId();
                this.seriesFlagLayout.setVisibility(View.GONE);
                this.seriesIdLayout.setVisibility(View.GONE);
                this.nameEdit.setText(String.valueOf(svBean.getSv_name()));
                this.yearEdit.setText(String.valueOf(svBean.getSv_year()));
                this.directorLayout.setVisibility(View.GONE);
                this.screenWriterLayout.setVisibility(View.GONE);
                this.makeEdit.setText(String.valueOf(svBean.getAuthor()));
                this.logoEdit.setText(String.valueOf(svBean.getLogo()));
                this.amountLayout.setVisibility(View.GONE);
                this.localFlagEdit.setText(String.valueOf(svBean.getLocalflag()));
                this.localUrlEdit.setText(String.valueOf(svBean.getLocalurl()));
                this.remoteFlagEdit.setText(String.valueOf(svBean.getRemoteflag()));
                this.remoteUrlEdit.setText(String.valueOf(svBean.getRemoteurl()));
                this.containerEdit.setText(String.valueOf(svBean.getContainer()));
                this.codeVEdit.setText(String.valueOf(svBean.getCodev()));
                this.codeAEdit.setText(String.valueOf(svBean.getCodea()));
                this.subTypeLayout.setVisibility(View.GONE);
                this.subTeamLayout.setVisibility(View.GONE);
                this.lastWatchLayout.setVisibility(View.GONE);
                this.remarkEdit.setText(String.valueOf(svBean.getRemark()));
                this.svType.setText(String.valueOf(svBean.getSv_type()));
            } break;
        }
    }



    @Override
    public void onClick(View view) {
        // 新增
        if (from.equals(SevxConsts.LIST)) {
            switch (type) {
                // 新增动漫
                case SevxConsts.ANIMATION: {
                    String addAnimationJson = this.getAnimationAddJson();
                    if (addAnimationJson != null){
                        new Thread(() -> {
                            if (httpHelper.animationAdd(addAnimationJson)) {
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

                // 新增电影
                case SevxConsts.FILM: {
                    String addFilmJson = this.getAddFilmJson();
                    if (addFilmJson != null){
                        new Thread(() -> {
                            if (httpHelper.filmAdd(addFilmJson)) {
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

                case SevxConsts.TV:{
                    String addTvJson = this.getAddTvJson();
                    if (addTvJson != null){
                        new Thread(() -> {
                            if (httpHelper.tvAdd(addTvJson)) {
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

                case SevxConsts.SV:{
                    String addSvJson = this.getAddSvJson();
                    if (addSvJson != null){
                        new Thread(() -> {
                            if (httpHelper.svAdd(addSvJson)) {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show());
                                editActivity.finish();
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


        // 更新
        if (from.equals(SevxConsts.DETAILS)) {
            switch (type) {
                // 修改动漫
                case SevxConsts.ANIMATION: {
                    String animationUpdateJson = this.getAnimationUpdateJson();
                    new Thread(() -> {
                        if (httpHelper.animationUpdate(animationUpdateJson)) {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            editActivity.finish();
                        } else {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                } break;

                case SevxConsts.FILM: {
                    String filmUpdateJson = this.getUpdateFilmJson();
                    new Thread(() -> {
                        if (httpHelper.filmUpdate(filmUpdateJson)) {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            editActivity.finish();
                        } else {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                } break;

                case SevxConsts.TV:{
                    String tvUpdateJson = this.getUpdateTvJson();
                    new Thread(() -> {
                        if (httpHelper.tvUpdate(tvUpdateJson)) {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            editActivity.finish();
                        } else {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                } break;

                case SevxConsts.SV:{
                    String svUpdateJson = this.getUpdateSvJson();
                    new Thread(() -> {
                        if (httpHelper.svUpdate(svUpdateJson)) {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                            editActivity.finish();
                        } else {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                }
            }
        }
    }


    /**
     * 获取更新动漫实体 JSON
     * @return jsonString
     */
    private String getAnimationUpdateJson(){
        // 实例化一个修改动漫实体类
        AnimationUpdateBean bean = new AnimationUpdateBean();
        try {
            // 数据填充
            bean.setId(id);
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setAnimation_name(nameEdit.getText().toString());
            bean.setAnimation_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored){
            return null;
        }
        return new Gson().toJson(bean);
    }

    /**
     * 获取添加动漫Json
     * @return jsonString
     */
    private String getAnimationAddJson(){
        // 实例化一个 添加动漫 实体类
        AnimationAddBean bean = new AnimationAddBean();
        try {
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setAnimation_name(nameEdit.getText().toString());
            bean.setAnimation_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getAddFilmJson(){
        FilmAddBean bean = new FilmAddBean();
        try {
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setFilm_name(nameEdit.getText().toString());
            bean.setFilm_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getUpdateFilmJson(){
        FilmUpdateBean bean = new FilmUpdateBean();
        try {
            // 数据填充
            bean.setId(id);
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setFilm_name(nameEdit.getText().toString());
            bean.setFilm_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored){
            return null;
        }
        return new Gson().toJson(bean);

    }

    private String getAddTvJson(){
        TvAddBean bean = new TvAddBean();
        try {
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setTv_name(nameEdit.getText().toString());
            bean.setTv_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getUpdateTvJson(){
        TvUpdateBean bean = new TvUpdateBean();
        try {
            // 数据填充
            bean.setId(id);
            bean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            bean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            bean.setTv_name(nameEdit.getText().toString());
            bean.setTv_year(yearEdit.getText().toString());
            bean.setDirector(directorEdit.getText().toString());
            bean.setScreenwriter(screenWriterEdit.getText().toString());
            bean.setMake(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setSubtype(subTypeEdit.getText().toString());
            bean.setSubteam(subTeamEdit.getText().toString());
            bean.setLastwatch(lastWatchEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored){
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getAddSvJson(){
        // 实例化一个 添加动漫 实体类
        SvAddBean bean = new SvAddBean();
        try {
            bean.setSv_type(svType.getText().toString());
            bean.setSv_name(nameEdit.getText().toString());
            bean.setSv_year(yearEdit.getText().toString());
            bean.setAuthor(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        return new Gson().toJson(bean);
    }

    private String getUpdateSvJson(){
        SvUpdateBean bean = new SvUpdateBean();
        try {
            // 数据填充
            bean.setId(id);
            bean.setSv_name(nameEdit.getText().toString());
            bean.setSv_year(yearEdit.getText().toString());
            bean.setAuthor(makeEdit.getText().toString());
            bean.setLogo(logoEdit.getText().toString());
            bean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            bean.setLocalurl(localUrlEdit.getText().toString());
            bean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            bean.setRemoteurl(remoteUrlEdit.getText().toString());
            bean.setContainer(containerEdit.getText().toString());
            bean.setCodev(codeVEdit.getText().toString());
            bean.setCodea(codeAEdit.getText().toString());
            bean.setRemark(remarkEdit.getText().toString());
            bean.setUname(uName);
            bean.setUpassword(uPassword);
        } catch (Exception ignored){
            return null;
        }
        return new Gson().toJson(bean);
    }

}