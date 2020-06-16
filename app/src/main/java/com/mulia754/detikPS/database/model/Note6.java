package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note6 {
    private String uid6;
    private String title6;
    private String content6;
    private String waktu6;
    private String cover6;

    public Note6() {
    }

    public Note6(String uid6, String title6, String content6, String waktu6, String cover6) {
        this.uid6 = uid6;

        this.title6 = title6;
        this.content6 = content6;
        this.waktu6 = waktu6;
        this.cover6 = cover6;
    }


    public String getTitle6() {
        return title6;
    }

    public String getContent6() {
        return content6;
    }
    public String getCover6() {
        return cover6;
    }

    public String getId6()  {
        return content6;
    }
    public String getWaktu6() {        return waktu6;
    }


}
