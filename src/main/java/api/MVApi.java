package api;

import com.alibaba.fastjson.JSON;
import util.NetWorkUtil;

public class MVApi {
    private final String cookie;

    public MVApi(String cookie) {
        this.cookie = cookie;
    }

    public String getMVUrl(String mvId) {
        String obj = NetWorkUtil.sendByGetUrl("/mv/url?id=" + mvId, cookie);
        System.out.println(obj);
        return JSON.parseObject(obj).getJSONObject("data").getString("url");
    }
}
