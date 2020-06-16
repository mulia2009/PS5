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
import com.mulia754.detikPS.database.adapter.NotesAdapter6;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note6;
import com.mulia754.detikPS.database.presenter.NotessPresenter4;
import com.mulia754.detikPS.database.presenter.NotessPresenter6;
import com.mulia754.detikPS.database.view.NotesView6;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class aadepan_persit extends Fragment implements NotesView6, NavigationView.OnNavigationItemSelectedListener {

    String url6 = " ";
    private RecyclerView mRvNotes6;
    private NotesAdapter6 mNotesAdapter6;
    private ArrayList<Note6> mNotes6;
    private NotessPresenter6 mNotesPresenter6;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager6;
    public WebView myWebView;

    public aadepan_persit() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.aadepan_persit, container, false );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes6 =  view.findViewById(R.id.rv_note);
        mNotes6 = new ArrayList<Note6>();
        mNotesAdapter6 = new NotesAdapter6(getContext(), mNotes6);
        mNotesPresenter6 = new NotessPresenter6(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes6.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes6.setAdapter(mNotesAdapter6);
        Collections.reverse(mNotes6);
        mRvNotes6.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager6 = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager6.setReverseLayout(true);
        mLayoutManager6.setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes6.setLayoutManager(mLayoutManager6);
        mRvNotes6.setAdapter(mNotesAdapter6);
        mNotesPresenter6.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter6.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter6.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void refreshNoteList6(List<Note6> notes6) {
        mNotes6.clear();
        mNotes6.addAll(notes6);
        mNotesAdapter6.notifyDataSetChanged();
        /*perintah klik recyclerview*/
        mRvNotes6.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    url6 = mNotes6.get(position).getCover6().toString();
                    i.putExtra("Value",url6);
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