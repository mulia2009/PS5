package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.model.Note2;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public class NotesAdapter2 extends RecyclerView.Adapter<NotesAdapter2.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext2;
    private List<Note2> mNotes2;
    private ImageView imageViewa;
    public NotesAdapter2(Context context2, List<Note2> notes2) {
        mContext2 = context2;
        mNotes2 = notes2;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView2 = inflater.inflate(R.layout.item_header, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView2);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note2 note2 = mNotes2.get(position);

        holder.getTvTitle2().setText(note2.getTitle2());
        holder.getTvContent2().setText(note2.getContent2());
        holder.getTvWaktu2().setText(note2.getWaktu2());
        holder.getTvCover2().setText(note2.getCover2());

        holder.setImage2(note2.getContent2());
    }

    @Override
    public int getItemCount() {        return mNotes2.size();
    }


    private Context getmContext2() {
        return mContext2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle2;
        private TextView tvCover2;


        private TextView tvContent2;

        private TextView tvWaktu2;
        ImageView imageView2;
        public ViewHolder(View itemView2) {
            super(itemView2);

            tvTitle2 = (TextView) itemView2.findViewById(R.id.tv_note_title);
            tvContent2 = (TextView) itemView2.findViewById(R.id.tv_note_content);
            imageView2=(ImageView)itemView2.findViewById(R.id.img_url);
            tvCover2 = (TextView) itemView2.findViewById(R.id.tvcover);

            tvWaktu2 = (TextView) itemView2.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle2() {
            return tvTitle2;
        }
        public TextView getTvWaktu2() {
            return tvWaktu2;
        }
        public TextView getTvCover2() {
            return tvCover2;
        }

        public TextView getTvContent2() {
            return tvContent2;
        }
        public void setImage2(String image2)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image2).into(imageView2);
        }
    }
}
