package com.davidups.moviemvp.movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/now_playing?api_key=c80439488364417bc2a4fd25612df122&language=en-US")
    Call<NowPlayingMovies> getLastMovies();

}
