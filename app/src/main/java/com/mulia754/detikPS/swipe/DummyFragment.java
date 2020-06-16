package com.mulia754.detikPS.swipe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter4;
import com.mulia754.detikPS.berita_Video;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.presenter.NotessPresenter4;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyFragment extends Fragment implements NotesView4, NavigationView.OnNavigationItemSelectedListener {

    String url4 = " ";
    private RecyclerView mRvNotes4;
    private NotesAdapter4 mNotesAdapter4;
    private ArrayList<Note4> mNotes4;
    private NotessPresenter4 mNotesPresenter4;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager4;
      public WebView myWebView;

    public DummyFragment() {}
    @SuppressLint("ValidFragment")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.aadepanfragment_dummy, container, false );
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes4 =  view.findViewById(R.id.rv_note);
        mNotes4 = new ArrayList<Note4>();
        mNotesAdapter4 = new NotesAdapter4(getContext(), mNotes4);
        mNotesPresenter4 = new NotessPresenter4(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes4.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes4.setAdapter(mNotesAdapter4);
        Collections.reverse(mNotes4);
        mRvNotes4.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager4 = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager4.setReverseLayout(true);
        mLayoutManager4.setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes4.setLayoutManager(mLayoutManager4);
        mRvNotes4.setAdapter(mNotesAdapter4);
        mNotesPresenter4.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter4.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter4.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void refreshNoteList4(List<Note4> notes4) {
            mNotes4.clear();
            mNotes4.addAll(notes4);
            mNotesAdapter4.notifyDataSetChanged();
            /*perintah klik recyclerview*/
            mRvNotes4.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                        url4 = mNotes4.get(position).getCover4().toString();
                        i.putExtra("Value",url4);
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