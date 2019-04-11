package com.maiajam.talabat.ui.activites;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maiajam.talabat.R;
import com.maiajam.talabat.helpers.HelperMethods;
import com.maiajam.talabat.ui.fragments.LoginFragment;

import java.util.Locale;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        HelperMethods.setRTL("ar",getBaseContext());
        HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(),new LoginFragment(),R.id.uerLogin_Frame,null);
    }
}
