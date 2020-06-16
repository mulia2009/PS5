package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note5;
import com.mulia754.detikPS.database.view.NotesView4;
import com.mulia754.detikPS.database.view.NotesView5;

import java.util.ArrayList;

/**
 * Created by dani on 4/4/17.
 */

public class NotessPresenter5 {

    private static final String TAG5 = NotessPresenter5.class.getSimpleName();

    private final NotesView5 notesView5;

    private DatabaseReference mNoteRef5;
    private ValueEventListener mNotesEventListener5;

    public NotessPresenter5(NotesView5 notesView5) {
        this.notesView5 = notesView5;
    }

    public void onCreate() {
        mNoteRef5 = FirebaseDatabase.getInstance().getReference().child("notes5");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note5> notes5 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note5 note5 = noteSnapshot.getValue(Note5.class);
                    notes5.add(note5);
                }

                notesView5.refreshNoteList5(notes5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG5, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef5.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener5 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener5 != null) {
            mNoteRef5.removeEventListener(mNotesEventListener5);
        }
    }
}
