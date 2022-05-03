package top.sakura70s.sevx.helper;


import org.json.JSONObject;


import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpHelper {
    public Request loginRequest(String url, JSONObject json) {
        // 规定请求类型
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
        // 创建一个 RequestBody
        RequestBody requestBody = RequestBody.Companion.create(json.toString(), JSON);
        // 创建一个请求对象
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }
}
