package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note8;
import com.mulia754.detikPS.database.view.NotesView8;

import java.util.ArrayList;

/**
 * Created by dani on 8/8/17.
 */

public class NotessPresenter8 {

    private static final String TAG8 = NotessPresenter8.class.getSimpleName();

    private final NotesView8 notesView8;

    private DatabaseReference mNoteRef8;
    private ValueEventListener mNotesEventListener8;

    public NotessPresenter8(NotesView8 notesView8) {
        this.notesView8 = notesView8;
    }

    public void onCreate() {
        mNoteRef8 = FirebaseDatabase.getInstance().getReference().child("notes8");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note8> notes8 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note8 note8 = noteSnapshot.getValue(Note8.class);
                    notes8.add(note8);
                }

                notesView8.refreshNoteList8(notes8);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG8, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef8.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener8 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener8 != null) {
            mNoteRef8.removeEventListener(mNotesEventListener8);
        }
    }
}
