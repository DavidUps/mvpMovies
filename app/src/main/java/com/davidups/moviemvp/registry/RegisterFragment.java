package com.davidups.moviemvp.registry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.davidups.moviemvp.R;
import com.davidups.moviemvp.login.LoginContract;
import com.davidups.moviemvp.user.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment implements RegisterContract.View {

    @BindView(R.id.etEmail)
    TextView etEmail;
    @BindView(R.id.etPassword)
    TextView etPassword;
    @BindView(R.id.btnRegistry)
    Button btnRegistry;
    @BindView(R.id.pbLoader)
    ProgressBar pbLoader;

    private RegisterContract.Presenter registerContract;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerContract = new RegisterPresenter(this);
        ButterKnife.bind(this, view);

        initLayout();
    }

    private void initLayout() {

        btnRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(etEmail.getText().toString(), etPassword.getText().toString());
                if (registerContract.isValidForm(user)) {
                    registerContract.attemptRegistry(user);
                }
            }
        });
    }

    @Override
    public void onNavigatorHome() {
        getActivity().finish();
    }

    @Override
    public void displayEmailError(String error) {
        etEmail.setError(error);
    }

    @Override
    public void displayPasswordError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void displayRegistryError(String error) {
        showMessage(error);
    }

    @Override
    public void displayLoadder(boolean loader) {
        int stateLoader = loader ? View.VISIBLE : View.GONE;
        pbLoader.setVisibility(stateLoader);
    }

    @Override
    public void setEnabledView(boolean enabled) {
        etEmail.setEnabled(enabled);
        etPassword.setEnabled(enabled);
        btnRegistry.setEnabled(enabled);
    }

    private void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
