package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note2 {
    private String uid2;
    private String title2;
    private String content2;
    private String waktu2;
    private String cover2;

    public Note2() {
    }

    public Note2(String uid2, String title2, String content2, String waktu2, String cover2) {
        this.uid2 = uid2;

        this.title2 = title2;
        this.content2 = content2;
        this.waktu2 = waktu2;
        this.cover2 = cover2;
    }


    public String getTitle2() {
        return title2;
    }

    public String getContent2() {
        return content2;
    }
    public String getCover2() {
        return cover2;
    }

    public String getId2()  {
        return content2;
    }
    public String getWaktu2() {        return waktu2;
    }


}
