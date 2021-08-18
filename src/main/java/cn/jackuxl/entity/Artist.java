package cn.jackuxl.entity;

import java.util.List;

public class Artist {
    private int id;
    private String name;
    private String picUrl;
    private List<String> alias;
    private int albumSize;
    private int picId;
    private String img1v1Url;
    private int img1v1;
    private String trans;
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

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }
    public List<String> getAlias() {
        return alias;
    }

    public void setAlbumSize(int albumSize) {
        this.albumSize = albumSize;
    }
    public int getAlbumsize() {
        return albumSize;
    }

    public void setPicid(int picId) {
        this.picId = picId;
    }
    public int getPicId() {
        return picId;
    }

    public void setImg1v1url(String img1v1url) {
        this.img1v1Url = img1v1url;
    }
    public String getImg1v1url() {
        return img1v1Url;
    }

    public void setImg1v1(int img1v1) {
        this.img1v1 = img1v1;
    }
    public int getImg1v1() {
        return img1v1;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getTrans() {
        return trans;
    }
}
