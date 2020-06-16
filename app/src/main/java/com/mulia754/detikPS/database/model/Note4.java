package com.mulia754.detikPS.database.model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Note4 {
    private String uid4;
    private String title4;
    private String content4;
    private String waktu4;
    private String cover4;

    public Note4() {
    }

    public Note4(String uid4, String title4, String content4, String waktu4, String cover4) {
        this.uid4 = uid4;

        this.title4 = title4;
        this.content4 = content4;
        this.waktu4 = waktu4;
        this.cover4 = cover4;
    }


    public String getTitle4() {
        return title4;
    }

    public String getContent4() {
        return content4;
    }
    public String getCover4() {
        return cover4;
    }

    public String getId4()  {
        return content4;
    }
    public String getWaktu4() {        return waktu4;
    }


}
