package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class Note {
    Integer viewType;
    private String uid;
    private String title;
    private String content;
    private String waktu;
    private String cover;

    public Note() {
    }

    public Note(String uid, String title, String content, String waktu, String cover) {
        this.uid = uid;

        this.title = title;
        this.content = content;
        this.waktu = waktu;
        this.cover = cover;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public String getCover() {
        return cover;
    }

    public String getId()  {
        return content;
    }
    public String getWaktu() {        return waktu;
    }


}
