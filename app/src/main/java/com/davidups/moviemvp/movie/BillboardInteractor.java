package com.davidups.moviemvp.movie;

import com.davidups.moviemvp.Constants;
import com.davidups.moviemvp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillboardInteractor implements BillboardContract.Interactor {

    private Constants constants = new Constants();
    private MovieApiService service = RetrofitInstance.getRetrofitInstance().create(MovieApiService.class);


    @Override
    public void getLastMovies(final OnFinishedListener onFinishedListener) {

        MovieApiService service = RetrofitInstance.getRetrofitInstance().create(MovieApiService.class);

        Call<NowPlayingMovies> call = service.getLastMovies();

        call.enqueue(new Callback<NowPlayingMovies>() {

            @Override
            public void onResponse(Call<NowPlayingMovies> call, Response<NowPlayingMovies> response) {
                onFinishedListener.onFinished(response.body().getResults());

            }

            @Override
            public void onFailure(Call<NowPlayingMovies> call, Throwable throwable) {
                onFinishedListener.onFailure(throwable);
            }
        });


    }
}
