package top.sakura70s.sevx.helpers;

import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestHelper {
    /**
     * 登录接口构建
     * @return request
     */
    public Request loginRequest(String url, String json) {
        // 规定请求类型
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
        // 创建一个 RequestBody
        RequestBody requestBody = RequestBody.Companion.create(json, JSON);
        // 创建一个请求对象
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 请求JSON数据
     * @return request
     */
    public Request getMediaJson(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    /**
     * 根据ID请求JSON
     * @param url URL
     * @param id ID
     * @return request
     */
    public Request getDetailsJson(String url, Integer id) {
        return new Request.Builder()
                .url(url + id)
                .get()
                .build();
    }

    /**
     * 根据名称请求JSOn
     * @return request
     */
    public Request getDetailsByName(String url, String name) {
        return new Request.Builder()
                .url(url + name)
                .get()
                .build();
    }

    /**
     * 删除
     * @return request
     */
    public Request getDeleteRequest(String url, JSONObject json) {
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
        // 创建一个 RequestBody
        RequestBody requestBody = RequestBody.Companion.create(json.toString(), JSON);

        return new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();
    }

    /**
     * 更新接口构建
     * @param url Url
     * @param json Json
     * @return Request
     */
    public Request getUpdateRequest(String url, String json) {
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
        // 创建一个 RequestBody
        RequestBody requestBody = RequestBody.Companion.create(json, JSON);
        return new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
    }

    /**
     * 添加接口构建
     * @param url Url
     * @param json json
     * @return Request
     */
    public Request getAddRequest(String url, String json){
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.Companion.create(json, JSON);
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

}
