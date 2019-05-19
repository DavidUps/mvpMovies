package com.davidups.moviemvp.login;

import android.support.annotation.NonNull;

import com.davidups.moviemvp.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor implements LoginContract.Interactor {

    private LoginContract.CompleteListener completeListener;
    private FirebaseAuth firebaseAuth;

    public LoginInteractor(LoginContract.CompleteListener completeListener) {
        this.completeListener = completeListener;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void performSignin(User user) {
       firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   completeListener.onSuccess();
               }else{
                   completeListener.onError();
               }
           }
       });
    }
}
