package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note8 {
    private String uid8;
    private String title8;
    private String content8;
    private String waktu8;
    private String cover8;

    public Note8() {
    }

    public Note8(String uid8, String title8, String content8, String waktu8, String cover8) {
        this.uid8 = uid8;

        this.title8 = title8;
        this.content8 = content8;
        this.waktu8 = waktu8;
        this.cover8 = cover8;
    }


    public String getTitle8() {
        return title8;
    }

    public String getContent8() {
        return content8;
    }
    public String getCover8() {
        return cover8;
    }

    public String getId8()  {
        return content8;
    }
    public String getWaktu8() {        return waktu8;
    }


}
