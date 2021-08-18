package cn.jackuxl.entity;

public class Album {
    private int id;
    private String name;
    private Artist artist;
    private int publishTime;
    private int size;
    private int copyrightId;
    private int status;
    private int picId;
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

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }
    public int getPublishTime() {
        return publishTime;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
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

    public void setPicId(int picId) {
        this.picId = picId;
    }
    public int getPicId() {
        return picId;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    public int getMark() {
        return mark;
    }
}
