package com.mulia754.detikPS.database.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulia754.detikPS.database.model.Note;


public class NotePresenter {

    private DatabaseReference mNoteRef;

    public NotePresenter() {
        mNoteRef = FirebaseDatabase.getInstance().getReference().child("notes");
    }

    public void create(String title, String content, String waktu, String cover) {
        String key = mNoteRef.push().getKey();
        Note note = new Note(key,content, title, waktu,cover);

        mNoteRef.child(key).setValue(note);
    }
}
