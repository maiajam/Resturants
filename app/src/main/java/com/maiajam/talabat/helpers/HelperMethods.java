package com.maiajam.talabat.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.maiajam.talabat.R;

import java.util.Locale;

public class HelperMethods {


    public static boolean checkConnection(Context activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        return info != null
                && info.isConnectedOrConnecting()
                && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    //

    public static void beginTransaction(android.support.v4.app.FragmentTransaction fragmentTransaction, android.support.v4.app.Fragment fragment, int frameId, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    //
    public static void setRTL(String lang,Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale(lang));
        context.getResources().updateConfiguration(configuration, null);
    }
    //
    public static boolean checkValidate(Context context, String phoneNo, String password) {

        if(TextUtils.isEmpty(phoneNo))
        {
            Toast.makeText(context,context.getString(R.string.Toast_Enter_Phoneno),Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(context,context.getString(R.string.Toast_Enter_pass),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
