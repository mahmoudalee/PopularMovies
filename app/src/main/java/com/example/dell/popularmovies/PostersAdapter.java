package com.example.dell.popularmovies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.zip.Inflater;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHoder> {

    public interface onPosterClickListener{
        void onPosterClick(int posterIndex);
    }

    private onPosterClickListener onPosterClickListener;

    public PostersAdapter (onPosterClickListener listener){
        onPosterClickListener = listener;
    }
    @NonNull
    @Override
    public PosterViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =inflater.inflate(R.layout.poster_item, viewGroup , false);

        return new PosterViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHoder posterViewHoder, int i) {
        posterViewHoder.posterImage.setImageResource(R.drawable.poster);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PosterViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView posterImage;
        public PosterViewHoder(@NonNull View itemView) {
            super(itemView);
            posterImage =(ImageView) itemView.findViewById(R.id.iv_poster_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int postersIndex = getAdapterPosition();
            onPosterClickListener.onPosterClick(postersIndex);
        }
    }
}
