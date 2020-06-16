package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note6;
import com.mulia754.detikPS.database.view.NotesView6;

import java.util.ArrayList;

/**
 * Created by dani on 6/6/17.
 */

public class NotessPresenter6 {

    private static final String TAG6 = NotessPresenter6.class.getSimpleName();

    private final NotesView6 notesView6;

    private DatabaseReference mNoteRef6;
    private ValueEventListener mNotesEventListener6;

    public NotessPresenter6(NotesView6 notesView6) {
        this.notesView6 = notesView6;
    }

    public void onCreate() {
        mNoteRef6 = FirebaseDatabase.getInstance().getReference().child("notes6");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note6> notes6 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note6 note6 = noteSnapshot.getValue(Note6.class);
                    notes6.add(note6);
                }

                notesView6.refreshNoteList6(notes6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG6, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef6.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener6 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener6 != null) {
            mNoteRef6.removeEventListener(mNotesEventListener6);
        }
    }
}
