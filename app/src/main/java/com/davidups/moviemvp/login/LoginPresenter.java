package com.davidups.moviemvp.login;

import android.util.Patterns;

import com.davidups.moviemvp.user.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.CompleteListener {

    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    LoginPresenter(LoginContract.View view){
        this.view = view;
        this.interactor = new LoginInteractor(this);
    }

    @Override
    public void attemptSignin(User user) {
        if (view != null){
            view.setEnabledView(false);
            view.displayLoadder(true);
        }
        interactor.performSignin(user);

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
            view.displaySigninError("Error en la autentificación");
        }
    }
}
