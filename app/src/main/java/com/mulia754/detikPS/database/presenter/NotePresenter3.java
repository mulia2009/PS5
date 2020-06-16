package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note3;


public class NotePresenter3 {

    private DatabaseReference mNoteRef3;

    public NotePresenter3() {
        mNoteRef3 = FirebaseDatabase.getInstance().getReference().child("notes3");
    }

    public void create(String title3, String content3, String waktu3, String cover3) {
        String key3 = mNoteRef3.push().getKey();
        Note3 note3 = new Note3(key3,content3, title3, waktu3,cover3);

        mNoteRef3.child(key3).setValue(note3);
    }
}
