package com.davidups.moviemvp.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.davidups.moviemvp.MainActivity;
import com.davidups.moviemvp.R;
import com.davidups.moviemvp.movie.BillboardFragment;
import com.davidups.moviemvp.registry.RegisterFragment;
import com.davidups.moviemvp.user.User;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment implements LoginContract.View {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.pbLoader)
    ProgressBar pbLoader;
    @BindView(R.id.tvRegister)
    TextView tvRegister;

    private LoginContract.Presenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginPresenter = new LoginPresenter(this);
        ButterKnife.bind(this,view);

        initListeners();

    }

    private void initListeners() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(etEmail.getText().toString(), etPassword.getText().toString());
                if(loginPresenter.isValidForm(user)){
                    loginPresenter.attemptSignin(user);
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterFragment();
            }
        });
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
    public void displaySigninError(String error) {
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
        btnLogin.setEnabled(enabled);
    }

    @Override
    public void openBillboardFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clMaster, new BillboardFragment()).addToBackStack("billboardFragment").commit();

    }

    @Override
    public void openRegisterFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clMaster, new RegisterFragment()).addToBackStack("registerFragment").commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroid();
    }

    private void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
