package com.mulia754.detikPS.database.view;

import com.mulia754.detikPS.database.model.Note;

import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public interface NotesView {
    void refreshNoteList(List<Note> notes);
}
