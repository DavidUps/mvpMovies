package com.davidups.moviemvp.registry;

import com.davidups.moviemvp.user.User;

public interface RegisterContract {

    interface View{
        void onNavigatorHome();
        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displayRegistryError(String error);
        void displayLoadder(boolean loader);
        void setEnabledView(boolean enabled);
        //void openMainFragment();
    }

    interface Presenter{
        void attemptRegistry(User user);
        boolean isValidForm(User user);
        void onDestroid();

    }

    interface Interactor {
        void performRegistry(User user);

    }

    interface CompleteListener{
        void onSuccess();
        void onError();

    }
}
