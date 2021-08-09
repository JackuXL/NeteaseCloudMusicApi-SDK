package util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * @author JackuXL
 */
public class NetWorkUtil {
    public static String domain;

    /**
     * ����������
     * ����������ַǰ
     *
     * @param domain ����������ʽ��http(s)://example.com��
     */
    public static void setDomain(String domain) {
        NetWorkUtil.domain = domain;
    }

    /**
     * GET����
     *
     * @param url    ��ַ��ɾ����������
     * @param cookie Cookie����ΪNull��
     * @return ������
     */
    public static String sendByGetUrl(String url, String cookie) {
        if (cookie == null) {
            cookie = "";
        }
        String result;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request;
        request = new Request.Builder()
                .addHeader("Cookie", cookie)
                .url(NetWorkUtil.domain + url)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            result = Objects.requireNonNull(response.body()).string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}