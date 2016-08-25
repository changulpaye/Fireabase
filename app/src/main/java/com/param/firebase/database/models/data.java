package com.param.firebase.database.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by pchangul on 8/25/2016.
 */

@IgnoreExtraProperties
public class Data {

    private String id;
    private String name;
    private String desc;
    private String url;
    private Long like;
    private boolean cflag;

    @Exclude
    public boolean isTopicSelected() {
        return topicSelected;
    }
    @Exclude
    public void setTopicSelected(boolean topicSelected) {
        this.topicSelected = topicSelected;
    }
    @Exclude
    private boolean topicSelected;


    public Data(String id, String name, String desc, String url, Long like, boolean cflag, boolean sflag) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.like = like;
        this.cflag = cflag;
        this.sflag = sflag;
    }

    public boolean isSflag() {
        return sflag;
    }

    public void setSflag(boolean sflag) {
        this.sflag = sflag;
    }

    public boolean isCflag() {
        return cflag;
    }

    public void setCflag(boolean cflag) {
        this.cflag = cflag;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean sflag;

    public Data() {
    }
}

