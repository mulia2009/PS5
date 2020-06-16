package com.mulia754.detikPS.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.mulia754.detikPS.R;

public class LoginFragment extends Fragment {

    TextInputEditText userName, password;
    Button login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false);

        userName = view.findViewById(R.id.et_username);
        password = view.findViewById(R.id.et_password);
        login = view.findViewById(R.id.btn_login);
        

        return view;
    }
}
