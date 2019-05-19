package com.davidups.moviemvp.login;

import com.davidups.moviemvp.user.User;

public interface LoginContract {

    interface View{
        void onNavigatorHome();
        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displaySigninError(String error);
        void displayLoadder(boolean loader);
        void setEnabledView(boolean enabled);
        void openRegisterFragment();
    }

    interface Presenter{
        void attemptSignin(User user);
        boolean isValidForm(User user);
        void onDestroid();

    }

    interface Interactor {
        void performSignin(User user);

    }

    interface CompleteListener{
        void onSuccess();
        void onError();

    }
}
