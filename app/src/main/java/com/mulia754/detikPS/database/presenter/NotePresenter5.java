package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note5;


public class NotePresenter5 {

    private DatabaseReference mNoteRef5;

    public NotePresenter5() {
        mNoteRef5 = FirebaseDatabase.getInstance().getReference().child("notes5");
    }

    public void create(String title5, String content5, String waktu5, String cover5) {
        String key5 = mNoteRef5.push().getKey();
        Note5 note5 = new Note5(key5,content5, title5, waktu5,cover5);

        mNoteRef5.child(key5).setValue(note5);
    }
}
