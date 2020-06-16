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
import com.mulia754.detikPS.database.adapter.NotesAdapter8;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note8;
import com.mulia754.detikPS.database.presenter.NotessPresenter4;
import com.mulia754.detikPS.database.presenter.NotessPresenter8;
import com.mulia754.detikPS.database.view.NotesView8;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class aadepan_olahraga extends Fragment implements NotesView8, NavigationView.OnNavigationItemSelectedListener {

    String url8 = " ";
    private RecyclerView mRvNotes8;
    private NotesAdapter8 mNotesAdapter8;
    private ArrayList<Note8> mNotes8;
    private NotessPresenter8 mNotesPresenter8;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager8;
    public WebView myWebView;

    public aadepan_olahraga() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.aadepan_olahraga, container, false );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes8 =  view.findViewById(R.id.rv_note);
        mNotes8 = new ArrayList<Note8>();
        mNotesAdapter8 = new NotesAdapter8(getContext(), mNotes8);
        mNotesPresenter8 = new NotessPresenter8(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes8.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes8.setAdapter(mNotesAdapter8);
        Collections.reverse(mNotes8);
        mRvNotes8.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager8 = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager8.setReverseLayout(true);
        mLayoutManager8.setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes8.setLayoutManager(mLayoutManager8);
        mRvNotes8.setAdapter(mNotesAdapter8);
        mNotesPresenter8.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter8.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter8.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void refreshNoteList8(List<Note8> notes8) {
        mNotes8.clear();
        mNotes8.addAll(notes8);
        mNotesAdapter8.notifyDataSetChanged();
        /*perintah klik recyclerview*/
        mRvNotes8.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    url8 = mNotes8.get(position).getCover8().toString();
                    i.putExtra("Value",url8);
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