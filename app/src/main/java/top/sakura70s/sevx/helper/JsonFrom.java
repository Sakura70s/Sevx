package top.sakura70s.sevx.helper;


import org.json.JSONException;
import org.json.JSONObject;

public class JsonFrom {

    public JSONObject loginJson(String name, String password){
        // 实例化 json
        JSONObject json = new JSONObject();
        // 填充数据
        try {
            json.put("uname", name);
            json.put("upassword", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 返回
        return json;
    }

}
