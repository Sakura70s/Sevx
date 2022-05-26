package top.sakura70s.sevx.helpers;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.ComicBean;
import top.sakura70s.sevx.beans.MusicBean;
import top.sakura70s.sevx.beans.NovelBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.beans.VideoFilmBean;
import top.sakura70s.sevx.beans.VideoSvBean;
import top.sakura70s.sevx.beans.VideoTvBean;

public class HttpHelper {

    OkHttpClient okHttpClient;
    Gson gson;
    RequestHelper requestHelper;

    public HttpHelper() {
        super();
        this.okHttpClient = new OkHttpClient();
        this.gson = new Gson();
        this.requestHelper = new RequestHelper();
    }


    /**
     * 获取动漫列表
     * @param animationRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<VideoAnimationBean>
     */
    public List<VideoAnimationBean> getAnimationList(Request animationRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(animationRequest).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                VideoAnimationBean[] animationBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, VideoAnimationBean[].class);
                return Arrays.asList(animationBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取动漫详情
     * @param id AnimationID
     * @return VideoAnimationBean
     */
    public VideoAnimationBean getAnimationById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_ANIMATION_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, VideoAnimationBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 根据名称获取动漫
     * @param name AnimationName
     * @return AnimationList
     */
    public List<VideoAnimationBean> getAnimationListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.VIDEO_ANIMATION_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                VideoAnimationBean[] animationBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, VideoAnimationBean[].class);
                return Arrays.asList(animationBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 更新动漫
     * @param json UpdateAnimationJson
     * @return Boolean
     */
    public Boolean animationUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.VIDEO_ANIMATION_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加动漫
     * @param json AddAnimationJson
     * @return Boolean
     */
    public Boolean animationAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.VIDEO_ANIMATION_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取电影列表
     * @param filmRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<VideoFilmBean>
     */
    public List<VideoFilmBean> getFilmList(Request filmRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(filmRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                VideoFilmBean[] filmBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, VideoFilmBean[].class);
                return Arrays.asList(filmBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取Tv
     * @param tvRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<VideoTvBean>
     */
    public List<VideoTvBean> getTvList(Request tvRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(tvRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                VideoTvBean[] tvBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, VideoTvBean[].class);
                return Arrays.asList(tvBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取Sv
     * @param svRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<VideoSvBean>
     */
    public List<VideoSvBean> getSvList(Request svRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(svRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                VideoSvBean[] svBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, VideoSvBean[].class);
                return Arrays.asList(svBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取Music
     * @param musicRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<MusicBean>
     */
    public List<MusicBean> getMusicList(Request musicRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(musicRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                MusicBean[] musicBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, MusicBean[].class);
                return Arrays.asList(musicBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取小说
     * @param novelRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<NovelBean>
     */
    public List<NovelBean> getNovelList(Request novelRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(novelRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                NovelBean[] novelBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, NovelBean[].class);
                return Arrays.asList(novelBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取漫画列表
     * @param comicRequest Request
     * @param okHttpClient OkHttpClient
     * @param gson Gson
     * @return List<ComicBean>
     */
    public List<ComicBean> getComicList(Request comicRequest, OkHttpClient okHttpClient, Gson gson){
        try {
            Response response = okHttpClient.newCall(comicRequest).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                ComicBean[] comicBeans = gson.fromJson(responseBody != null ? responseBody.string() : null, ComicBean[].class);
                return Arrays.asList(comicBeans);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取电影详情
     * @param id FilmId
     * @return VideoFilmBean
     */
    public VideoFilmBean getFilmById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_ANIMATION_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, VideoFilmBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    public VideoTvBean getTvById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_ANIMATION_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, VideoTvBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }

    public VideoSvBean getSvById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_ANIMATION_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, VideoSvBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }
}
