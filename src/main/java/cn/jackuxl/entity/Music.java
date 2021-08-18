package cn.jackuxl.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private int id;
    private String name;
    private List<Artist> artists;
    private Album album;
    private int duration;
    private int copyrightId;
    private int status;
    private List<String> alias;
    private int rtype;
    private int ftype;
    private int mvid;
    private int fee;
    private String rUrl;
    private int mark;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public List<Artist> getArtists() {
        return artists;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    public Album getAlbum() {
        return album;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }
    public int getCopyrightId() {
        return copyrightId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }
    public List<String> getAlias() {
        return alias;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }
    public int getRtype() {
        return rtype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }
    public int getFtype() {
        return ftype;
    }

    public void setMvid(int mvid) {
        this.mvid = mvid;
    }
    public int getMvid() {
        return mvid;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
    public int getFee() {
        return fee;
    }

    public void setRUrl(String rUrl) {
        this.rUrl = rUrl;
    }
    public String getRUrl() {
        return rUrl;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    public int getMark() {
        return mark;
    }
    public static List<Music> toMusicArrayList(JSONArray songs){
        List<Music> results = new ArrayList<>();
        for(int i = 0;i<songs.size();i++){
            results.add(JSON.toJavaObject(songs.getJSONObject(i),Music.class));
        }
        return results;
    }

}
