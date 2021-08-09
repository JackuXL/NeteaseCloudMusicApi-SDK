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
     * ��ȡ˽��FM
     *
     * @return FM���ص������б�
     */
    public JSONArray getFM() {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/personal_fm", cookie)).getJSONArray("data");
    }

    /**
     * ��������
     *
     * @param keyword �ؼ���
     * @return �������
     */
    public JSONArray searchMusic(String keyword) {
        JSONObject content = JSON.parseObject(NetWorkUtil.sendByGetUrl("/search?keywords=" + keyword, cookie));
        if (content.getJSONObject("result") == null) {
            return new JSONArray();
        }
        return content.getJSONObject("result").getJSONArray("songs");
    }

    /**
     * ��ȡ����
     *
     * @return ���ѹؼ����б�
     */
    public JSONArray getHot() {
        String result = NetWorkUtil.sendByGetUrl("/search/hot", cookie);
        if (result == null) {
            return new JSONArray();
        }
        return JSON.parseObject(result).getJSONObject("result").getJSONArray("hots");
    }

    /**
     * ��ȡ������Ϣ
     *
     * @param id ����id
     * @return ������Ϣ
     */
    public JSONArray getMusicDetail(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/detail?ids=" + id, cookie)).getJSONArray("songs");
    }

    /**
     * ��ȡ������Ϣ��������
     *
     * @param ids ����id�б�
     * @return ������Ϣ
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
     * ��ȡר������
     *
     * @param albumId ר��id
     * @return ��������
     */
    public String getMusicCover(String albumId) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/album?id=" + albumId, cookie))
                .getJSONArray("songs").getJSONObject(0).getJSONObject("al").getString("picUrl");
    }

    /**
     * ��ȡ��������
     *
     * @param id ����id
     * @return ��������
     */
    public String getMusicUrl(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/song/url?id=" + id, cookie))
                .getJSONArray("data").getJSONObject(0).getString("url");

    }

    /**
     * ��ȡ�������ӣ�������
     *
     * @param ids ����id
     * @return ��������
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
     * ��ȡ���
     *
     * @param id ����id
     * @return ���
     */
    public String getMusicLyric(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/lyric?id=" + id, cookie))
                .getJSONObject("lrc").getString("lyric");
    }

    /**
     * ϲ������
     *
     * @param id ����id
     * @return �Ƿ�ɹ�
     */
    public Boolean likeMusic(String id, Boolean like) {
        if (like) {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl("/like?id=" + id, cookie)).getInteger("code") == 200;
        } else {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl("/like?like=false&id=" + id, cookie)).getInteger("code") == 200;
        }
    }

    /**
     * ��ȡ����
     *
     * @param id ����id
     * @return �����б�
     */
    public JSONArray getHotComment(String id) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl("/comment/music?id=" + id, cookie)).getJSONArray("hotComments");
    }

    /**
     * ��������
     *
     * @param id      ����id
     * @param content ����
     * @return �Ƿ�ɹ�
     */
    public Boolean sendComment(String id, String content) {
        return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment?t=1&type=0&id=%s&content=%s", id, content), cookie)).getInteger("code") == 200;
    }

    /**
     * ��������
     *
     * @param id   ��Դid
     * @param cid  ����id
     * @param like �Ƿ����
     * @return �Ƿ�ɹ�
     */
    public Boolean likeComment(String id, String cid, Boolean like) {
        if (like) {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment/like?id=%s&cid=%s&t=1&type=1", id, cid), cookie)).getInteger("code") == 200;
        } else {
            return JSON.parseObject(NetWorkUtil.sendByGetUrl(String.format("/comment/like?id=%s&cid=%s&t=1&type=0", id, cid), cookie)).getInteger("code") == 200;
        }
    }


}
