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
import com.mulia754.detikPS.database.model.Note5;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 4/4/17.
 */

public class NotesAdapter5 extends RecyclerView.Adapter<NotesAdapter5.ViewHolder> {
    private static final int HEADER = 0;
    private static final int NORMAL = 1;

    private int lastTopValue = 1;

    private Context mContext5;
    private List<Note5> mNotes5;
    private ImageView imageViewa;
    public NotesAdapter5(Context context5, ArrayList<Note5> notes5) {
        mContext5 = context5;
        mNotes5 = notes5;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View noteView5 = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView5);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note5 note5 = mNotes5.get(position);

        holder.getTvTitle5().setText(note5.getTitle5());
        holder.getTvContent5().setText(note5.getContent5());
        holder.getTvWaktu5().setText(note5.getWaktu5());
        holder.getTvCover5().setText(note5.getCover5());

        holder.setImage5(note5.getContent5());
    }

    @Override
    public int getItemCount() {        return mNotes5.size();
    }


    private Context getmContext5() {return mContext5;}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle5;
        private TextView tvCover5;


        private TextView tvContent5;

        private TextView tvWaktu5;
        ImageView imageView5;
        public ViewHolder(View itemView5) {
            super(itemView5);

            tvTitle5 = (TextView) itemView5.findViewById(R.id.tv_note_title);
            tvContent5 = (TextView) itemView5.findViewById(R.id.tv_note_content);
            imageView5=(ImageView)itemView5.findViewById(R.id.img_url);
            tvCover5 = (TextView) itemView5.findViewById(R.id.tvcover);

            tvWaktu5 = (TextView) itemView5.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle5() {
            return tvTitle5;
        }
        public TextView getTvWaktu5() {
            return tvWaktu5;
        }
        public TextView getTvCover5() {
            return tvCover5;
        }

        public TextView getTvContent5() {
            return tvContent5;
        }
        public void setImage5(String image5)
        {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image5).into(imageView5);
        }
    }
}
