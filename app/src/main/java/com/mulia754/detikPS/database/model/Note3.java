package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note3 {
    private String uid3;
    private String title3;
    private String content3;
    private String waktu3;
    private String cover3;

    public Note3() {
    }

    public Note3(String uid3, String title3, String content3, String waktu3, String cover3) {
        this.uid3 = uid3;

        this.title3 = title3;
        this.content3 = content3;
        this.waktu3 = waktu3;
        this.cover3 = cover3;
    }


    public String getTitle3() {
        return title3;
    }

    public String getContent3() {
        return content3;
    }
    public String getCover3() {
        return cover3;
    }

    public String getId3()  {
        return content3;
    }
    public String getWaktu3() {        return waktu3;
    }


}
