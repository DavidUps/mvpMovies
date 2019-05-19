package com.davidups.moviemvp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davidups.moviemvp.login.LoginFragment;
import com.davidups.moviemvp.registry.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLoginFragment();
    }

    void openLoginFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.clMaster, new LoginFragment()).addToBackStack("loginFragment").commit();
    }
}
