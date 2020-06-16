package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.model.Note7;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 7/7/17.
 */

public class NotesAdapter7 extends RecyclerView.Adapter<NotesAdapter7.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext7;
    private List<Note7> mNotes7;
    private ImageView imageViewa;
    public NotesAdapter7(Context context7, ArrayList<Note7> notes7) {
        mContext7 = context7;
        mNotes7 = notes7;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView7 = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView7);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note7 note7 = mNotes7.get(position);

        holder.getTvTitle7().setText(note7.getTitle7());
        holder.getTvContent7().setText(note7.getContent7());
        holder.getTvWaktu7().setText(note7.getWaktu7());
        holder.getTvCover7().setText(note7.getCover7());

        holder.setImage7(note7.getContent7());
    }

    @Override
    public int getItemCount() {        return mNotes7.size();
    }


    private Context getmContext7() {return mContext7;}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle7;
        private TextView tvCover7;


        private TextView tvContent7;

        private TextView tvWaktu7;
        ImageView imageView7;
        public ViewHolder(View itemView7) {
            super(itemView7);

            tvTitle7 = (TextView) itemView7.findViewById(R.id.tv_note_title);
            tvContent7 = (TextView) itemView7.findViewById(R.id.tv_note_content);
            imageView7=(ImageView)itemView7.findViewById(R.id.img_url);
            tvCover7 = (TextView) itemView7.findViewById(R.id.tvcover);

            tvWaktu7 = (TextView) itemView7.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle7() {
            return tvTitle7;
        }
        public TextView getTvWaktu7() {
            return tvWaktu7;
        }
        public TextView getTvCover7() {
            return tvCover7;
        }

        public TextView getTvContent7() {
            return tvContent7;
        }
        public void setImage7(String image7)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image7).into(imageView7);
        }
    }
}
