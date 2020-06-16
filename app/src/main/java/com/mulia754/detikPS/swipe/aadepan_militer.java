package com.mulia754.detikPS.swipe;

import android.annotation.SuppressLint;
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
import com.mulia754.detikPS.database.adapter.NotesAdapter;
import com.mulia754.detikPS.berita_Video;
import com.mulia754.detikPS.database.adapter.NotesAdapter4;
import com.mulia754.detikPS.database.adapter.NotesAdapter5;
import com.mulia754.detikPS.database.model.Note;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note5;
import com.mulia754.detikPS.database.presenter.NotesPresenter;
import com.mulia754.detikPS.database.presenter.NotessPresenter4;
import com.mulia754.detikPS.database.presenter.NotessPresenter5;
import com.mulia754.detikPS.database.view.NotesView4;
import com.mulia754.detikPS.database.view.NotesView5;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class aadepan_militer extends Fragment implements NotesView5, NavigationView.OnNavigationItemSelectedListener {

    String url5 = " ";
    private RecyclerView mRvNotes5;
    private NotesAdapter5 mNotesAdapter5;
    private ArrayList<Note5> mNotes5;
    private NotessPresenter5 mNotesPresenter5;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager5;
    public WebView myWebView;



    public aadepan_militer() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.aadepanfragment_dummy, container, false );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes5 =  view.findViewById(R.id.rv_note);
        mNotes5 = new ArrayList<Note5>();
        mNotesAdapter5 = new NotesAdapter5(getContext(), mNotes5);
        mNotesPresenter5 = new NotessPresenter5(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes5.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes5.setAdapter(mNotesAdapter5);
        Collections.reverse(mNotes5);
        mRvNotes5.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager5 = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager5.setReverseLayout(true);
        mLayoutManager5.setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes5.setLayoutManager(mLayoutManager5);
        mRvNotes5.setAdapter(mNotesAdapter5);
        mNotesPresenter5.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter5.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter5.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void refreshNoteList5(List<Note5> notes5) {
        mNotes5.clear();
        mNotes5.addAll(notes5);
        mNotesAdapter5.notifyDataSetChanged();
        /*perintah klik recyclerview*/
        mRvNotes5.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    url5 = mNotes5.get(position).getCover5().toString();
                    i.putExtra("Value",url5);
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