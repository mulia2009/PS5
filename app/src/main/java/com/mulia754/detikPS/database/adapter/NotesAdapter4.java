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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public class NotesAdapter4 extends RecyclerView.Adapter<NotesAdapter4.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext4;
    private List<Note4> mNotes4;
    private ImageView imageViewa;
    public NotesAdapter4(Context context4, ArrayList<Note4> notes4) {
        mContext4 = context4;
        mNotes4 = notes4;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView4 = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView4);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note4 note4 = mNotes4.get(position);

        holder.getTvTitle4().setText(note4.getTitle4());
        holder.getTvContent4().setText(note4.getContent4());
        holder.getTvWaktu4().setText(note4.getWaktu4());
        holder.getTvCover4().setText(note4.getCover4());

        holder.setImage4(note4.getContent4());
    }

    @Override
    public int getItemCount() {        return mNotes4.size();
    }


    private Context getmContext4() {return mContext4;}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle4;
        private TextView tvCover4;


        private TextView tvContent4;

        private TextView tvWaktu4;
        ImageView imageView4;
        public ViewHolder(View itemView4) {
            super(itemView4);

            tvTitle4 = (TextView) itemView4.findViewById(R.id.tv_note_title);
            tvContent4 = (TextView) itemView4.findViewById(R.id.tv_note_content);
            imageView4=(ImageView)itemView4.findViewById(R.id.img_url);
            tvCover4 = (TextView) itemView4.findViewById(R.id.tvcover);

            tvWaktu4 = (TextView) itemView4.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle4() {
            return tvTitle4;
        }
        public TextView getTvWaktu4() {
            return tvWaktu4;
        }
        public TextView getTvCover4() {
            return tvCover4;
        }

        public TextView getTvContent4() {
            return tvContent4;
        }
        public void setImage4(String image4)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image4).into(imageView4);
        }
    }
}
