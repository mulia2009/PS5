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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter;
import com.mulia754.detikPS.berita_Video;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.database.model.Note;
import com.mulia754.detikPS.database.presenter.NotesPresenter;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Terkini extends Fragment implements NotesView , NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = bottom_videox.class.getSimpleName();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView gbrx;
    private TextView tvlink;
    private InterstitialAd interstitial;
    String url  = " ";
    private RecyclerView mRvNotes ;
    private NotesAdapter  mNotesAdapter ;
    private ArrayList<Note> mNotes ;
    private NotesPresenter  mNotesPresenter ;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager ;
    public WebView myWebView;
    // TODO: Rename and change types of parameters

    int dummyColor;
    public Terkini() {}
    @SuppressLint("ValidFragment")
    public Terkini(int color) {
        this.dummyColor = color;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.aadepanfragment_dummy, container, false );
        final FrameLayout frameLayout = view.findViewById( R.id.frame_layout_dummy );
        frameLayout.setBackgroundColor( dummyColor );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity().getBaseContext() );
        mRvNotes  =  view.findViewById(R.id.rv_note);
        mNotes  = new ArrayList<Note >();
        mNotesAdapter  = new NotesAdapter (getContext(), mNotes );
        mNotesPresenter  = new NotesPresenter (this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes .addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes .setAdapter(mNotesAdapter );
        Collections.reverse(mNotes );
        mRvNotes .setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mLayoutManager  = new LinearLayoutManager(getActivity().getBaseContext());
        mLayoutManager .setReverseLayout(true);
        mLayoutManager .setStackFromEnd(true);
// And now set it to the RecyclerView
        mRvNotes .setLayoutManager(mLayoutManager );
        mRvNotes .setAdapter(mNotesAdapter );
        mNotesPresenter.onCreate();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mNotesPresenter.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        mNotesPresenter.onStop();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void refreshNoteList (List<Note > notes ) {

        mNotes .clear();
        mNotes .addAll(notes );
        mNotesAdapter .notifyDataSetChanged();
        /*perintah klik recyclerview*/
        mRvNotes .addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    url  = mNotes .get(position).getCover ().toString();
                    i.putExtra("Value",url );
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