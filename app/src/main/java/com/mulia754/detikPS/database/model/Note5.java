package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note5 {
    private String uid5;
    private String title5;
    private String content5;
    private String waktu5;
    private String cover5;

    public Note5() {
    }

    public Note5(String uid5, String title5, String content5, String waktu5, String cover5) {
        this.uid5 = uid5;

        this.title5 = title5;
        this.content5 = content5;
        this.waktu5 = waktu5;
        this.cover5 = cover5;
    }


    public String getTitle5() {
        return title5;
    }

    public String getContent5() {
        return content5;
    }
    public String getCover5() {
        return cover5;
    }

    public String getId5()  {
        return content5;
    }
    public String getWaktu5() {        return waktu5;
    }


}
