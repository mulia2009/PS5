package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note7;
import com.mulia754.detikPS.database.view.NotesView7;

import java.util.ArrayList;

/**
 * Created by dani on 7/7/17.
 */

public class NotessPresenter7 {

    private static final String TAG7 = NotessPresenter7.class.getSimpleName();

    private final NotesView7 notesView7;

    private DatabaseReference mNoteRef7;
    private ValueEventListener mNotesEventListener7;

    public NotessPresenter7(NotesView7 notesView7) {
        this.notesView7 = notesView7;
    }

    public void onCreate() {
        mNoteRef7 = FirebaseDatabase.getInstance().getReference().child("notes7");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note7> notes7 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note7 note7 = noteSnapshot.getValue(Note7.class);
                    notes7.add(note7);
                }

                notesView7.refreshNoteList7(notes7);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG7, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef7.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener7 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener7 != null) {
            mNoteRef7.removeEventListener(mNotesEventListener7);
        }
    }
}
