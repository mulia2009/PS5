package com.mulia754.detikPS.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.model.Note;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mulia on 4/4/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private static final int DEFAULT_VIEW_TYPE = 1;
    private static final int NATIVE_AD_VIEW_TYPE = 2;
    private static final int ADMOB_TYPE = 3;


    private Context mContext;
    private List<Note> mNotes;

    public NotesAdapter(Context context, List<Note> notes) {
        mContext = context;
        mNotes = notes;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return NATIVE_AD_VIEW_TYPE;
        }
        if (position==3){
               return ADMOB_TYPE;
            }

        return DEFAULT_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return mNotes.size() ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //ViewHolder viewHolder = new ViewHolder(noteView);


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            default:
                View noteView = inflater.inflate(R.layout.item_note, parent, false);

                ViewHolder ItemViewHolder = new ViewHolder(noteView);

                return ItemViewHolder;
            case NATIVE_AD_VIEW_TYPE:
                View noteView2 = inflater.inflate(R.layout.list_header, parent, false);

                ViewHolder HeaderViewHolder = new ViewHolder(noteView2);

                return HeaderViewHolder;


        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.getTvTitle().setText(note.getTitle());
        holder.getTvContent().setText(note.getContent());
        holder.getTvWaktu().setText(note.getWaktu());
        holder.getTvCover().setText(note.getCover());

        holder.setImage(note.getContent());

    }


    private Context getContext() {
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvCover;


        private TextView tvContent;

        private TextView tvWaktu;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle =  itemView.findViewById(R.id.tv_note_title);
            tvContent =  itemView.findViewById(R.id.tv_note_content);
            imageView =  itemView.findViewById(R.id.img_url);
            tvCover = itemView.findViewById(R.id.tvcover);

            tvWaktu = itemView.findViewById(R.id.tvwaktu);

        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvWaktu() {
            return tvWaktu;
        }

        public TextView getTvCover() {
            return tvCover;
        }

        public TextView getTvContent() {
            return tvContent;
        }

        public void setImage(String image) {
            //Picasso.get(itemView.getContext()).load(image).into(imageView);
            Picasso.get().load(image).into(imageView);
        }


    }
}