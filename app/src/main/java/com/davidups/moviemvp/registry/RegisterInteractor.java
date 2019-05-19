package com.davidups.moviemvp.registry;

import android.support.annotation.NonNull;

import com.davidups.moviemvp.login.LoginContract;
import com.davidups.moviemvp.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterInteractor implements RegisterContract.Interactor {

    private RegisterContract.CompleteListener completeListener;
    private FirebaseAuth firebaseAuth;

    public RegisterInteractor(RegisterContract.CompleteListener completeListener) {
        this.completeListener = completeListener;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void performRegistry(User user) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    completeListener.onSuccess();
                } else {
                    completeListener.onError();
                }
            }
        });
    }
}
