package com.example.dell.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.media.JetPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.popularmovies.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

import static com.example.dell.popularmovies.MainActivity.postersData;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHoder> {

    Context context;
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
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.poster_item, viewGroup , false);

        return new PosterViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHoder posterViewHoder, int i) {

        String imagePath="https://image.tmdb.org/t/p/w185/"+JsonUtils.parseJson(postersData.get(i)).getImage();
        Picasso.with(context)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterViewHoder.posterImage);
    }

    @Override
    public int getItemCount() {
        return postersData.size();
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
