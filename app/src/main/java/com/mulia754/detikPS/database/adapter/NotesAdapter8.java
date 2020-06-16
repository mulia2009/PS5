package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.model.Note8;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 8/8/17.
 */
//UNTUK FRAGMENT MILITER
public class NotesAdapter8 extends RecyclerView.Adapter<NotesAdapter8.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext8;
    private List<Note8> mNotes8;
    private ImageView imageViewa;
    public NotesAdapter8(Context context8, ArrayList<Note8> notes8) {
        mContext8 = context8;
        mNotes8 = notes8;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView8 = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView8);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note8 note8 = mNotes8.get(position);

        holder.getTvTitle8().setText(note8.getTitle8());
        holder.getTvContent8().setText(note8.getContent8());
        holder.getTvWaktu8().setText(note8.getWaktu8());
        holder.getTvCover8().setText(note8.getCover8());

        holder.setImage8(note8.getContent8());
    }

    @Override
    public int getItemCount() {        return mNotes8.size();
    }


    private Context getmContext8() {return mContext8;}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle8;
        private TextView tvCover8;


        private TextView tvContent8;

        private TextView tvWaktu8;
        ImageView imageView8;
        public ViewHolder(View itemView8) {
            super(itemView8);

            tvTitle8 = (TextView) itemView8.findViewById(R.id.tv_note_title);
            tvContent8 = (TextView) itemView8.findViewById(R.id.tv_note_content);
            imageView8=(ImageView)itemView8.findViewById(R.id.img_url);
            tvCover8 = (TextView) itemView8.findViewById(R.id.tvcover);

            tvWaktu8 = (TextView) itemView8.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle8() {
            return tvTitle8;
        }
        public TextView getTvWaktu8() {
            return tvWaktu8;
        }
        public TextView getTvCover8() {
            return tvCover8;
        }

        public TextView getTvContent8() {
            return tvContent8;
        }
        public void setImage8(String image8)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image8).into(imageView8);
        }
    }
}
