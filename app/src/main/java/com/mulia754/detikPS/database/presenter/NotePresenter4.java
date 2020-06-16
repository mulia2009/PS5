package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note4;


public class NotePresenter4 {

    private DatabaseReference mNoteRef4;

    public NotePresenter4() {
        mNoteRef4 = FirebaseDatabase.getInstance().getReference().child("notes4");
    }

    public void create(String title4, String content4, String waktu4, String cover4) {
        String key4 = mNoteRef4.push().getKey();
        Note4 note4 = new Note4(key4,content4, title4, waktu4,cover4);

        mNoteRef4.child(key4).setValue(note4);
    }
}
