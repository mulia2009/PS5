package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.model.Note4;
import com.mulia754.detikPS.database.model.Note6;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public class NotesAdapter6 extends RecyclerView.Adapter<NotesAdapter6.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext6;
    private List<Note6> mNotes6;
    private ImageView imageViewa;
    public NotesAdapter6(Context context6, ArrayList<Note6> notes6) {
        mContext6 = context6;
        mNotes6 = notes6;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView6 = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView6);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note6 note6 = mNotes6.get(position);

        holder.getTvTitle6().setText(note6.getTitle6());
        holder.getTvContent6().setText(note6.getContent6());
        holder.getTvWaktu6().setText(note6.getWaktu6());
        holder.getTvCover6().setText(note6.getCover6());

        holder.setImage6(note6.getContent6());
    }

    @Override
    public int getItemCount() {        return mNotes6.size();
    }


    private Context getmContext6() {return mContext6;}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle6;
        private TextView tvCover6;


        private TextView tvContent6;

        private TextView tvWaktu6;
        ImageView imageView6;
        public ViewHolder(View itemView6) {
            super(itemView6);

            tvTitle6 = (TextView) itemView6.findViewById(R.id.tv_note_title);
            tvContent6 = (TextView) itemView6.findViewById(R.id.tv_note_content);
            imageView6=(ImageView)itemView6.findViewById(R.id.img_url);
            tvCover6 = (TextView) itemView6.findViewById(R.id.tvcover);

            tvWaktu6 = (TextView) itemView6.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle6() {
            return tvTitle6;
        }
        public TextView getTvWaktu6() {
            return tvWaktu6;
        }
        public TextView getTvCover6() {
            return tvCover6;
        }

        public TextView getTvContent6() {
            return tvContent6;
        }
        public void setImage6(String image6)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image6).into(imageView6);
        }
    }
}
