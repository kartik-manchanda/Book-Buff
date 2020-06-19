package com.example.moviebuff.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.moviebuff.R;
import com.example.moviebuff.adapter.MovieAdapter;
import com.example.moviebuff.model.Movie;
import com.example.moviebuff.model.MoviePageResult;
import com.example.moviebuff.network.MovieBuilder;
import com.example.moviebuff.network.MovieService;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    RelativeLayout progressLayout;
    ProgressBar progressbar;
    RecyclerView.LayoutManager layoutManager;
    public int currentSortMode;
    public int page;
    public final String userKey="5bbafb113ae731ed4fe3d2e17ba040a2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=findViewById(R.id.recycler);
        progressLayout=findViewById(R.id.progressLayout);
        progressbar=findViewById(R.id.progressBar);
        progressLayout.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.VISIBLE);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            layoutManager= new GridLayoutManager(this,2);
        }else{
            layoutManager= new GridLayoutManager(this,4);
        }

        loadpage(1);
    }

    public void loadpage(int currentSortMode){
        MovieService movieService= MovieBuilder.buildService(MovieService.class);
        Call<MoviePageResult> call;
        if(currentSortMode==1){
            call=movieService.getPopularMovies(userKey);
        }else{
            call=movieService.getTopRatedMovies(userKey);
        }


        call.enqueue(new Callback<MoviePageResult>() {
            @Override
            public void onResponse(Call<MoviePageResult> call, Response<MoviePageResult> response) {
                progressLayout.setVisibility(View.GONE);
                List<Movie> movies=response.body().getMovieResult();
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(new MovieAdapter(MainActivity.this,movies));
            }

            @Override
            public void onFailure(Call<MoviePageResult> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to Retrieve data",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_sort,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //SortID 1 -> Popularity
        //SortID 2 -> Top rated
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                currentSortMode = 1;
                break;
            case R.id.sort_by_top:
                currentSortMode = 2;
                break;
        }
        loadpage(currentSortMode);
        return super.onOptionsItemSelected(item);

    }
}
