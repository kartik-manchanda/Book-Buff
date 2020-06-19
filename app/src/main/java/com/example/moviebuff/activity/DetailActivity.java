package com.example.moviebuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebuff.R;
import com.example.moviebuff.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView imgDetailImage;
    TextView txtDetailTitle;
    TextView txtDate;
    TextView txtRating;
    TextView txtOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetailImage=findViewById(R.id.imgDetailImage);
        txtDetailTitle=findViewById(R.id.txtDetailTitle);
        txtDate=findViewById(R.id.txtDate);
        txtRating=findViewById(R.id.txtRating);
        txtOverview=findViewById(R.id.txtOverview);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Movie movie=(Movie) bundle.getSerializable("movie");

        Picasso.get().load("https://image.tmdb.org/t/p/" +
                "w500"+movie.getPosterPath()).error(R.mipmap.ic_launcher).into(imgDetailImage);
        txtDetailTitle.setText(movie.getTitle());
        txtDate.setText(movie.getReleaseDate());
        txtRating.setText(movie.getVoteAverage()+"/10");
        txtOverview.setText(movie.getOverview());
    }
}
