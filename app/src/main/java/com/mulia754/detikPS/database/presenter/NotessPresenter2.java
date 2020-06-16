package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note2;
import com.mulia754.detikPS.database.view.NotesView2;

import java.util.ArrayList;

/**
 * Created by dani on 4/4/17.
 */

public class NotessPresenter2 {

    private static final String TAG2 = NotessPresenter2.class.getSimpleName();

    private final NotesView2 notesView2;

    private DatabaseReference mNoteRef2;
    private ValueEventListener mNotesEventListener2;

    public NotessPresenter2(NotesView2 notesView2) {
        this.notesView2 = notesView2;
    }

    public void onCreate() {
        mNoteRef2 = FirebaseDatabase.getInstance().getReference().child("notes2");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note2> notes2 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note2 note2 = noteSnapshot.getValue(Note2.class);
                    notes2.add(note2);
                }

                notesView2.refreshNoteList2(notes2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG2, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef2.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener2 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener2 != null) {
            mNoteRef2.removeEventListener(mNotesEventListener2);
        }
    }
}
