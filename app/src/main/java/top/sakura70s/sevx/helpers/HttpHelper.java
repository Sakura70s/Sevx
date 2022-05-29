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
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.beans.sv.VideoSvBean;
import top.sakura70s.sevx.beans.tv.VideoTvBean;

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
     * 获取电影详情
     * @param id FilmID
     * @return VideoFilmBean
     */
    public VideoFilmBean getFilmById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_FILM_GET_ID, id);
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

    /**
     * 根据名称获取电影
     * @param name FilmName
     * @return List<VideoFilmBean>
     */
    public List<VideoFilmBean> getFilmListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.VIDEO_FILM_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                VideoFilmBean[] filmBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, VideoFilmBean[].class);
                return Arrays.asList(filmBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新电影
     * @param json UpdateFilmJson
     * @return Boolean
     */
    public Boolean filmUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.VIDEO_FILM_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加电影
     * @param json AddFilmJson
     * @return Boolean
     */
    public Boolean filmAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.VIDEO_FILM_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * 获取Tv详情
     * @param id TvID
     * @return VideoTvBean
     */
    public VideoTvBean getTvById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_TV_GET_ID, id);
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
    /**
     * 根据名称获取Tv
     * @param name TvName
     * @return List<VideoTvBean>
     */
    public List<VideoTvBean> getTvListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.VIDEO_TV_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                VideoTvBean[] tvBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, VideoTvBean[].class);
                return Arrays.asList(tvBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新Tv
     * @param json UpdateTvJson
     * @return Boolean
     */
    public Boolean tvUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.VIDEO_TV_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加电影
     * @param json AddTvJson
     * @return Boolean
     */
    public Boolean tvAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.VIDEO_TV_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * 获取 SV 详情
     * @param id SvID
     * @return VideoSvBean
     */
    public VideoSvBean getSvById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.VIDEO_SV_GET_ID, id);
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

    /**
     * 根据名称获取 Sv
     * @param name SvName
     * @return List<VideoSvBean>
     */
    public List<VideoSvBean> getSvListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.VIDEO_SV_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                VideoSvBean[] svBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, VideoSvBean[].class);
                return Arrays.asList(svBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新 Sv
     * @param json UpdateSvJson
     * @return Boolean
     */
    public Boolean svUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.VIDEO_SV_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加 Sv
     * @param json AddSvJson
     * @return Boolean
     */
    public Boolean svAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.VIDEO_SV_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * 获取 Music 详情
     * @param id Music id
     * @return MusicBean
     */
    public MusicBean getMusicById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.MUSIC_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, MusicBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 根据名称获取 Music
     * @param name MusicName
     * @return List<MusicBean>
     */
    public List<MusicBean> getMusicListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.MUSIC_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                MusicBean[] musicBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, MusicBean[].class);
                return Arrays.asList(musicBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新 Music
     * @param json UpdateMusicJson
     * @return Boolean
     */
    public Boolean musicUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.MUSIC_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加 Music
     * @param json AddMusicJson
     * @return Boolean
     */
    public Boolean musicAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.MUSIC_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * 获取 Novel 详情
     * @param id Novel id
     * @return NovelBean
     */
    public NovelBean getNovelById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.NOVEL_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, NovelBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 根据名称获取 Novel
     * @param name NovelName
     * @return List<NovelBean>
     */
    public List<NovelBean> getNovelListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.NOVEL_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                NovelBean[] novelBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, NovelBean[].class);
                return Arrays.asList(novelBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新 Novel
     * @param json UpdateNovelJson
     * @return Boolean
     */
    public Boolean novelUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.NOVEL_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加 Novel
     * @param json AddNovelJson
     * @return Boolean
     */
    public Boolean novelAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.NOVEL_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * 获取 Comic 详情
     * @param id Comic id
     * @return ComicBean
     */
    public ComicBean getComicById(Integer id){
        Request request = requestHelper.getDetailsJson(SevxConsts.COMIC_GET_ID, id);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                return gson.fromJson(responseBody != null ? responseBody.string() : null, ComicBean.class);
            } else return null;
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 根据名称获取 Comic
     * @param name ComicName
     * @return List<ComicBean>
     */
    public List<ComicBean> getComicListForName(String name){
        Request request = requestHelper.getDetailsByName(SevxConsts.COMIC_GET_NAME, name);
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200){
                ResponseBody responseBody = response.body();
                ComicBean[] comicBeans = gson.fromJson(responseBody != null
                        ? responseBody.string() : null, ComicBean[].class);
                return Arrays.asList(comicBeans);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * 更新 Comic
     * @param json UpdateComicJson
     * @return Boolean
     */
    public Boolean comicUpdate(String json){
        Request request = requestHelper.getUpdateRequest(SevxConsts.COMIC_PUT_UPDATE, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 添加 Comic
     * @param json AddComicJson
     * @return Boolean
     */
    public Boolean comicAdd(String json){
        Request request = requestHelper.getAddRequest(SevxConsts.COMIC_POST_ADD, json);
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
