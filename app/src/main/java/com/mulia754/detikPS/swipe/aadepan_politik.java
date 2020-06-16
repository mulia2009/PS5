package com.mulia754.detikPS.swipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.material.navigation.NavigationView;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter4;
import com.mulia754.detikPS.berita_Video;
import com.mulia754.detikPS.database.adapter.NotesAdapter7;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note7;
import com.mulia754.detikPS.database.presenter.NotessPresenter4;
import com.mulia754.detikPS.database.presenter.NotessPresenter7;
import com.mulia754.detikPS.database.view.NotesView7;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class aadepan_politik extends Fragment implements NotesView7, NavigationView.OnNavigationItemSelectedListener {

    String url7 = " ";
    private RecyclerView mRvNotes7;
    private NotesAdapter7 mNotesAdapter7;
    private ArrayList<Note7> mNotes7;
    private NotessPresenter7 mNotesPresenter7;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager7;
    public WebView myWebView;

    public aadepan_politik() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.aadepan_politik, container, false );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes7 =  view.findViewById(R.id.rv_note);
        mNotes7 = new ArrayList<Note7>();
        mNotesAdapter7 = new NotesAdapter7(getContext(), mNotes7);
        mNotesPresenter7 = new NotessPresenter7(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes7.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes7.setAdapter(mNotesAdapter7);
        Collections.reverse(mNotes7);
        mRvNotes7.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager7 = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager7.setReverseLayout(true);
        mLayoutManager7.setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes7.setLayoutManager(mLayoutManager7);
        mRvNotes7.setAdapter(mNotesAdapter7);
        mNotesPresenter7.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter7.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter7.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void refreshNoteList7(List<Note7> notes7) {
        mNotes7.clear();
        mNotes7.addAll(notes7);
        mNotesAdapter7.notifyDataSetChanged();
        /*perintah klik recyclerview*/
        mRvNotes7.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity().getBaseContext(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(child);
                    Intent i;
                    i = new Intent(getActivity().getBaseContext(), berita_Video.class);
                    url7 = mNotes7.get(position).getCover7().toString();
                    i.putExtra("Value",url7);
                    startActivity(i);
                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        })
        ;
    }
}