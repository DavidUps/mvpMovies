package com.davidups.moviemvp.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.davidups.moviemvp.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BillboardActivity extends AppCompatActivity implements BillboardContract.View{

    @BindView(R.id.cvLastestFilms)
    CarouselView carouselView;

    @BindView(R.id.pbLoader)
    ProgressBar pbLoader;

    private BillboardContract.Presenter presenter;
    private ArrayList<Result> lastMovies;
    private String BASE_IMG_URL = "https://image.tmdb.org/t/p/w400";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard);
        ButterKnife.bind(this);

        initCarouserl();
        presenter = new BillboardPresenter(this, new BillboardInteractor());
        presenter.attmptGetLastMovie();


    }

    private void initCarouserl() {
        carouselView.setPageCount(0);
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(0);
    }

    @Override
    public void setDataFromLastMovies(ArrayList<Result> lastMovies) {
        this.lastMovies = lastMovies;
        carouselView.setPageCount(lastMovies.size());
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(7000);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(BASE_IMG_URL + lastMovies.get(position).getPosterPath()).fit().centerCrop().into(imageView);
        }
    };

    @Override
    public void onResponseFailure(Throwable throwable) {
        showMessage(throwable.toString());
    }

    @Override
    public void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openMovieDetail() {

    }

    private void showMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
