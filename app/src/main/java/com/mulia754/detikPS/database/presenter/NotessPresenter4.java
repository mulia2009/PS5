package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.view.NotesView4;

import java.util.ArrayList;

/**
 * Created by dani on 4/4/17.
 */

public class NotessPresenter4 {

    private static final String TAG4 = NotessPresenter4.class.getSimpleName();

    private final NotesView4 notesView4;

    private DatabaseReference mNoteRef4;
    private ValueEventListener mNotesEventListener4;

    public NotessPresenter4(NotesView4 notesView4) {
        this.notesView4 = notesView4;
    }

    public void onCreate() {
        mNoteRef4 = FirebaseDatabase.getInstance().getReference().child("notes4");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note4> notes4 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note4 note4 = noteSnapshot.getValue(Note4.class);
                    notes4.add(note4);
                }

                notesView4.refreshNoteList4(notes4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG4, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef4.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener4 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener4 != null) {
            mNoteRef4.removeEventListener(mNotesEventListener4);
        }
    }
}
