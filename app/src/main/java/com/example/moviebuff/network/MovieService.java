package com.example.moviebuff.network;


import com.example.moviebuff.model.Movie;
import com.example.moviebuff.model.MoviePageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MoviePageResult> getPopularMovies(@Query("api_key") String userKey);

    @GET("movie/top_rated")
    Call<MoviePageResult> getTopRatedMovies(@Query("api_key") String userKey);
}

