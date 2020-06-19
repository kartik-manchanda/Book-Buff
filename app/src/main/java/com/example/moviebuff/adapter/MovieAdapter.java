package com.example.moviebuff.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviebuff.R;
import com.example.moviebuff.activity.DetailActivity;
import com.example.moviebuff.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private Context mContext;
    private List<Movie> movies;

    public MovieAdapter(Context mContext, List<Movie> movies){
        this.mContext=mContext;
        this.movies=movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.single_row_recycler, parent, false);
            return new MovieAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie movie=movies.get(position);
        holder.txtMovieTitle.setText(movie.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/" +
        "w500"+movie.getPosterPath()).error(R.mipmap.ic_launcher).into(holder.imgMovieImage);
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("movie",movie);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgMovieImage;
        public TextView txtMovieTitle;
        public LinearLayout llContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovieImage=itemView.findViewById(R.id.imgMovieImage);
            txtMovieTitle=itemView.findViewById(R.id.txtMovieTitle);
            llContent=itemView.findViewById(R.id.llContent);
        }
    }
}
