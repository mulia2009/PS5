package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note7;


public class NotePresenter7 {

    private DatabaseReference mNoteRef7;

    public NotePresenter7() {
        mNoteRef7 = FirebaseDatabase.getInstance().getReference().child("notes7");
    }

    public void create(String title7, String content7, String waktu7, String cover7) {
        String key7 = mNoteRef7.push().getKey();
        Note7 note7 = new Note7(key7,content7, title7, waktu7,cover7);

        mNoteRef7.child(key7).setValue(note7);
    }
}
