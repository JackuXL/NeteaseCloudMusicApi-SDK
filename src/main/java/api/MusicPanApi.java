package api;

import cn.jackuxl.entity.Music;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

public class MusicPanApi {
    private final String cookie;

    public MusicPanApi(String cookie) {
        this.cookie = cookie;
    }

    public List<Music> getMusicList() {
        JSONArray data = JSON.parseObject(NetWorkUtil.sendByGetUrl("/user/cloud", cookie)).getJSONArray("data");
        return data == null ? new ArrayList<>() : Music.toMusicArrayList(data);
    }

    /**
     * 获取云盘音乐信息
     *
     * @param id 音乐id
     * @return 音乐信息
     */
    public List<Music> getMusicDetail(String id) {
        return Music.toMusicArrayList(JSON.parseObject(NetWorkUtil.sendByGetUrl("/user/cloud/detail?id=" + id, cookie)).getJSONArray("songs"));
    }

    /**
     * 获取云盘音乐信息（批量）
     *
     * @param ids 音乐id列表
     * @return 音乐信息
     */
    public List<Music> getMusicDetail(String[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            stringBuilder.append(ids[i]);
            if ((i + 1) != ids.length) {
                stringBuilder.append(",");
            }
        }
        return Music.toMusicArrayList(JSON.parseObject(NetWorkUtil.sendByGetUrl("/user/cloud/detail?id=" + stringBuilder, cookie)).getJSONArray("songs"));
    }

    /**
     * 删除云盘音乐
     *
     * @param music 音乐对象
     * @return 状态码
     */
    public int deleteMusic(Music music) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/user/cloud/del?id=" + music.getId(), cookie)).getInteger("code");
    }

    /**
     * 获取音乐链接
     *
     * @param music 音乐对象
     * @return 音乐链接
     */
    public String getMusicUrl(Music music) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/url?id=" + music.getId(), cookie))
                .getJSONArray("data").getJSONObject(0).getString("url");

    }

    /**
     * 获取音乐链接（批量）
     *
     * @param ids 音乐id
     * @return 音乐链接
     */
    public String[] getMusicUrl(String[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            stringBuilder.append(ids[i]);
            if ((i + 1) != ids.length) {
                stringBuilder.append(",");
            }
        }
        JSONArray data = JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/url?id=" + stringBuilder, cookie)).getJSONArray("data");
        String[] result = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            JSONObject tmp = data.getJSONObject(i);
            result[searchArrayForIndex(ids, tmp.getString("id"))] = tmp.getString("url");
        }
        return result;
    }

    private int searchArrayForIndex(String[] array, String str) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
