package com.davidups.moviemvp.movie;

import java.util.ArrayList;

public class BillboardPresenter implements BillboardContract.Presenter, BillboardContract.Interactor.OnFinishedListener {

    private BillboardContract.View view;
    private BillboardContract.Interactor interactor;

    public BillboardPresenter(BillboardContract.View view, BillboardContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void attmptGetLastMovie() {
        if (isNetworkingConnection()) {
            view.showProgress();
            interactor.getLastMovies(this);
        } else {
            onFailure(new Throwable("No internet conextion"));
        }
    }

    @Override
    public boolean isNetworkingConnection() {
        return true;
    }

    @Override
    public void onDestroid() {
        view = null;
    }

    @Override
    public void onFinished(ArrayList<Result> lastMovies) {
        if (view != null){
            view.setDataFromLastMovies(lastMovies);
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
