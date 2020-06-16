package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.database.model.Note3;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public class NotesAdapter3 extends RecyclerView.Adapter<NotesAdapter3.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext3;
    private List<Note3> mNotes3;
    private ImageView imageViewa;
    public NotesAdapter3(bottom_videox context3, List<Note3> notes3) {
        mContext3 = context3;
        mNotes3 = notes3;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView3 = inflater.inflate(R.layout.item_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView3);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note3 note3 = mNotes3.get(position);

        holder.getTvTitle3().setText(note3.getTitle3());
        holder.getTvContent3().setText(note3.getContent3());
        holder.getTvWaktu3().setText(note3.getWaktu3());
        holder.getTvCover3().setText(note3.getCover3());

        holder.setImage3(note3.getContent3());
    }

    @Override
    public int getItemCount() {        return mNotes3.size();
    }


    private Context getmContext3() {
        return mContext3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle3;
        private TextView tvCover3;


        private TextView tvContent3;

        private TextView tvWaktu3;
        ImageView imageView3;
        public ViewHolder(View itemView3) {
            super(itemView3);

            tvTitle3 = (TextView) itemView3.findViewById(R.id.tv_note_title);
            tvContent3 = (TextView) itemView3.findViewById(R.id.tv_note_content);
            imageView3=(ImageView)itemView3.findViewById(R.id.img_url);
            tvCover3 = (TextView) itemView3.findViewById(R.id.tvcover);

            tvWaktu3 = (TextView) itemView3.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle3() {
            return tvTitle3;
        }
        public TextView getTvWaktu3() {
            return tvWaktu3;
        }
        public TextView getTvCover3() {
            return tvCover3;
        }

        public TextView getTvContent3() {
            return tvContent3;
        }
        public void setImage3(String image3)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image3).into(imageView3);
        }
    }
}
