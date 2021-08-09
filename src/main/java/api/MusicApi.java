package api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.NetWorkUtil;

public class MusicApi {
    private final String cookie;

    public MusicApi(String cookie) {
        this.cookie = cookie;
    }

    /**
     * 获取私人FM
     *
     * @return FM返回的音乐列表
     */
    public JSONArray getFM() {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/personal_fm", cookie)).getJSONArray("data");
    }

    /**
     * 搜索音乐
     *
     * @param keyword 关键词
     * @return 搜索结果
     */
    public JSONArray searchMusic(String keyword) {
        JSONObject content = JSON.parseObject(NetWorkUtil.sendByGetUrl("/search?keywords=" + keyword, cookie));
        if (content.getJSONObject("result") == null) {
            return new JSONArray();
        }
        return content.getJSONObject("result").getJSONArray("songs");
    }

    /**
     * 获取热搜
     *
     * @return 热搜关键词列表
     */
    public JSONArray getHot() {
        String result = NetWorkUtil.sendByGetUrl("/search/hot", cookie);
        if (result == null) {
            return new JSONArray();
        }
        return JSON.parseObject(result).getJSONObject("result").getJSONArray("hots");
    }

    /**
     * 获取音乐信息
     *
     * @param id 音乐id
     * @return 音乐信息
     */
    public JSONArray getMusicDetail(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/detail?ids=" + id, cookie)).getJSONArray("songs");
    }

    /**
     * 获取音乐信息（批量）
     *
     * @param ids 音乐id列表
     * @return 音乐信息
     */
    public JSONArray getMusicDetail(String[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            stringBuilder.append(ids[i]);
            if ((i + 1) != ids.length) {
                stringBuilder.append(",");
            }
        }
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/detail?ids=" + stringBuilder, cookie)).getJSONArray("songs");
    }

    /**
     * 获取专辑封面
     *
     * @param albumId 专辑id
     * @return 封面链接
     */
    public String getMusicCover(String albumId) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/album?id=" + albumId, cookie))
                .getJSONArray("songs").getJSONObject(0).getJSONObject("al").getString("picUrl");
    }

    /**
     * 获取音乐链接
     *
     * @param id 音乐id
     * @return 音乐链接
     */
    public String getMusicUrl(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/url?id=" + id, cookie))
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

    /**
     * 获取歌词
     *
     * @param id 音乐id
     * @return 歌词
     */
    public String getMusicLyric(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/lyric?id=" + id, cookie))
                .getJSONObject("lrc").getString("lyric");
    }

    /**
     * 喜欢音乐
     *
     * @param id 音乐id
     * @return 是否成功
     */
    public Boolean likeMusic(String id, Boolean like) {
        if (like) {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl("/like?id=" + id, cookie)).getInteger("code") == 200;
        } else {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl("/like?like=false&id=" + id, cookie)).getInteger("code") == 200;
        }
    }

    /**
     * 获取热评
     *
     * @param id 音乐id
     * @return 热评列表
     */
    public JSONArray getHotComment(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/comment/music?id=" + id, cookie)).getJSONArray("hotComments");
    }

    /**
     * 发送评论
     *
     * @param id      音乐id
     * @param content 内容
     * @return 是否成功
     */
    public Boolean sendComment(String id, String content) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment?t=1&type=0&id=%s&content=%s", id, content), cookie)).getInteger("code") == 200;
    }

    /**
     * 点赞评论
     *
     * @param id   资源id
     * @param cid  评论id
     * @param like 是否点赞
     * @return 是否成功
     */
    public Boolean likeComment(String id, String cid, Boolean like) {
        if (like) {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment/like?id=%s&cid=%s&t=1&type=1", id, cid), cookie)).getInteger("code") == 200;
        } else {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment/like?id=%s&cid=%s&t=1&type=0", id, cid), cookie)).getInteger("code") == 200;
        }
    }


}
