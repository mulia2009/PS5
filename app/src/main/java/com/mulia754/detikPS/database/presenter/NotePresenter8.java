package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note8;


public class NotePresenter8 {

    private DatabaseReference mNoteRef8;

    public NotePresenter8() {
        mNoteRef8 = FirebaseDatabase.getInstance().getReference().child("notes8");
    }

    public void create(String title8, String content8, String waktu8, String cover8) {
        String key8 = mNoteRef8.push().getKey();
        Note8 note8 = new Note8(key8,content8, title8, waktu8,cover8);

        mNoteRef8.child(key8).setValue(note8);
    }
}
