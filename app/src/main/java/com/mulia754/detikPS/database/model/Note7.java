package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note7 {
    private String uid7;
    private String title7;
    private String content7;
    private String waktu7;
    private String cover7;

    public Note7() {
    }

    public Note7(String uid7, String title7, String content7, String waktu7, String cover7) {
        this.uid7 = uid7;

        this.title7 = title7;
        this.content7 = content7;
        this.waktu7 = waktu7;
        this.cover7 = cover7;
    }


    public String getTitle7() {
        return title7;
    }

    public String getContent7() {
        return content7;
    }
    public String getCover7() {
        return cover7;
    }

    public String getId7()  {
        return content7;
    }
    public String getWaktu7() {        return waktu7;
    }


}
