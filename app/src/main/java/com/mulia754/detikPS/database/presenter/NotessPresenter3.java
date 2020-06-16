package com.mulia754.detikPS.database.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulia754.detikPS.database.model.Note3;
import com.mulia754.detikPS.database.view.NotesView3;

import java.util.ArrayList;

/**
 * Created by dani on 4/4/17.
 */

public class NotessPresenter3 {

    private static final String TAG3 = NotessPresenter3.class.getSimpleName();

    private final NotesView3 notesView3;

    private DatabaseReference mNoteRef3;
    private ValueEventListener mNotesEventListener3;

    public NotessPresenter3(NotesView3 notesView3) {
        this.notesView3 = notesView3;
    }

    public void onCreate() {
        mNoteRef3 = FirebaseDatabase.getInstance().getReference().child("notes3");
    }

    public void onStart() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Note3> notes3 = new ArrayList<>();

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    Note3 note3 = noteSnapshot.getValue(Note3.class);
                    notes3.add(note3);
                }

                notesView3.refreshNoteList3(notes3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG3, "loadNote:onCancelled", databaseError.toException());
            }
        };

        mNoteRef3.orderByKey().addValueEventListener(valueEventListener);

        mNotesEventListener3 = valueEventListener;
    }

    public void onStop() {
        if (mNotesEventListener3 != null) {
            mNoteRef3.removeEventListener(mNotesEventListener3);
        }
    }
}
