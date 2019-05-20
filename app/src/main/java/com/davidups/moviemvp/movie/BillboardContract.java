package com.davidups.moviemvp.movie;

import java.util.ArrayList;

public interface BillboardContract {

    interface View {
        void setDataFromLastMovies(ArrayList<Result> lastMovies);

        void onResponseFailure(Throwable throwable);

        void showProgress();

        void hideProgress();

        void openMovieDetail();

    }

    interface Presenter {

        void attmptGetLastMovie();

        boolean isNetworkingConnection();

        void onDestroid();
    }

    interface Interactor {
        interface OnFinishedListener {
            void onFinished(ArrayList<Result> lastMovies);

            void onFailure(Throwable throwable);
        }

        void getLastMovies(OnFinishedListener onFinishedListener);
    }
}
