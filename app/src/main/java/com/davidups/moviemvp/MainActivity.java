package com.davidups.moviemvp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davidups.moviemvp.login.LoginFragment;
import com.davidups.moviemvp.movie.BillboardActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (firebaseAuth.getCurrentUser() != null) {
            openMovieActivity();
        } else {
            openLoginFragment();
        }
    }

    void openLoginFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.clMaster, new LoginFragment()).addToBackStack("loginFragment").commit();
    }

    public void openMovieActivity() {
        Intent intent = new Intent(this, BillboardActivity.class);
        startActivity(intent);
    }
}
