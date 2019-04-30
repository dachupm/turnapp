
package com.darshanvineeth.turn1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by sWoRd f!sH on 15-03-2019.
 */

public class editprofileFragment extends Fragment implements TextWatcher {

    EditText user_email;
    EditText user_phone;
    int email_flag = 0;
    int phone_flag = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        user_email = view.findViewById(R.id.user_email);
        user_phone = view.findViewById(R.id.user_phone);
        ImageButton save_profile = view.findViewById(R.id.save_profile);

        user_email.addTextChangedListener(editprofileFragment.this);
        user_phone.addTextChangedListener(editprofileFragment.this);
        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (email_flag == 0) {
                    user_email.requestFocus(); //If email not valid
                }else if (phone_flag == 0) {
                    user_phone.requestFocus(); //If phone_num not valid
                }else if(email_flag==1 && phone_flag==1) {
                    editor.putString("email", user_email.getText().toString());
                    editor.putString("phone", user_phone.getText().toString());
                    editor.apply();
                    Toast.makeText(getActivity(), "Profile was updated successfully", Toast.LENGTH_SHORT).show();
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                }
            }
        });


        return view;
    }

    boolean isEmailValid(CharSequence Email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!isEmailValid(user_email.getText().toString())) {
            user_email.setError("Please enter a valid email address");
        } else email_flag = 1;

        if (user_phone.length() < 10 || !user_phone.getText().toString().matches("[1-9][0-9]{9}")) {
            user_phone.setError("Please enter a valid phone number");
        } else phone_flag = 1;
    }
}
