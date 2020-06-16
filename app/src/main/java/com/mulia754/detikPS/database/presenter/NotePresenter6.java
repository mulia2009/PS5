package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note6;


public class NotePresenter6 {

    private DatabaseReference mNoteRef6;

    public NotePresenter6() {
        mNoteRef6 = FirebaseDatabase.getInstance().getReference().child("notes6");
    }

    public void create(String title6, String content6, String waktu6, String cover6) {
        String key6 = mNoteRef6.push().getKey();
        Note6 note6 = new Note6(key6,content6, title6, waktu6,cover6);

        mNoteRef6.child(key6).setValue(note6);
    }
}
