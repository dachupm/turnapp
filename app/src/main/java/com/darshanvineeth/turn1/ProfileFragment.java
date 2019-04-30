package com.darshanvineeth.turn1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sWoRd f!sH on 15-03-2019.
 */

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        ImageButton edit_profile = view.findViewById(R.id.edit_profile);
        Button logout = view.findViewById(R.id.logout);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new editprofileFragment()).commit();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("log_flag","0");
                editor.apply();
                Intent intent = new Intent(getActivity(),StartActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        String email = sharedPreferences.getString("email","");
        String phone = sharedPreferences.getString("phone","");

        TextView profile_email = view.findViewById(R.id.profile_email);
        profile_email.setText(email);
        TextView profile_phone = view.findViewById(R.id.profile_phone);
        profile_phone.setText(getString(R.string.country_code)+phone);

        return view;
    }
}
