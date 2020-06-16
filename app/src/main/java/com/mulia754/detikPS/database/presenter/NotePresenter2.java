package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note2;


public class NotePresenter2 {

    private DatabaseReference mNoteRef2;

    public NotePresenter2() {
        mNoteRef2 = FirebaseDatabase.getInstance().getReference().child("notes2");
    }

    public void create(String title2, String content2, String waktu2, String cover2) {
        String key2 = mNoteRef2.push().getKey();
        Note2 note2 = new Note2(key2,content2, title2, waktu2,cover2);

        mNoteRef2.child(key2).setValue(note2);
    }
}
