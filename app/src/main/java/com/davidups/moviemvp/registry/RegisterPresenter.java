package com.davidups.moviemvp.registry;

import android.util.Patterns;

import com.davidups.moviemvp.user.User;

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.CompleteListener {

    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    RegisterPresenter(RegisterContract.View view){
        this.view = view;
        this.interactor = new RegisterInteractor(this);
    }

    @Override
    public void attemptRegistry(User user) {
        if (view != null){
            view.setEnabledView(false);
            view.displayLoadder(true);
        }
        interactor.performRegistry(user);
    }

    @Override
    public boolean isValidForm(User user) {

        boolean isValid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()){
            isValid = false;
            view.displayEmailError("Formato Email incorrecto");
        }
        else if (user.getPassword().length() <= 5){
            isValid = false;
            view.displayPasswordError("La contraseña es demassido corta");
        }
        return isValid;
    }

    @Override
    public void onDestroid() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if (view != null){
            view.setEnabledView(true);
            view.displayLoadder(false);
            view.openBillboardFragment();
        }
    }

    @Override
    public void onError() {

        if (view != null) {
            view.setEnabledView(true);
            view.displayLoadder(false);
            view.displayRegistryError("Error en la autentificación");
        }
    }
}
