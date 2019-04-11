package com.maiajam.talabat.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.talabat.R;
import com.maiajam.talabat.data.TalabatApi.retrofit.ApiServeces;
import com.maiajam.talabat.data.models.login.Login;
import com.maiajam.talabat.data.models.loginData.LoginData;
import com.maiajam.talabat.helpers.HelperMethods;
import com.maiajam.talabat.ui.activites.ResturantListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.talabat.data.TalabatApi.retrofit.ApiServer.getClient;


public class LoginFragment extends Fragment {

    @BindView(R.id.Login_img_Logo)
    ImageView LoginImgLogo;
    @BindView(R.id.Login_ET_PhoneNo)
    EditText LoginETPhoneNo;
    @BindView(R.id.Login_ET_Passworde)
    EditText LoginETPassworde;
    @BindView(R.id.Login_ChBox_RemberMe)
    CheckBox LoginChBoxRemberMe;
    @BindView(R.id.Login_TextView_ForgetPass)
    TextView LoginTextViewForgetPass;
    @BindView(R.id.Login_B_Login)
    Button LoginBLogin;
    @BindView(R.id.Login_B_Reg)
    Button LoginBReg;
    Unbinder unbinder;
    private ApiServeces apiServeces;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiServeces = getClient().create(ApiServeces.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @OnClick({R.id.Login_B_Login, R.id.Login_B_Reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_B_Login:
                if (HelperMethods.checkValidate(getContext(), LoginETPhoneNo.getText().toString(), LoginETPassworde.getText().toString())) {
                    String phoneNo = LoginETPhoneNo.getText().toString();
                    String password = LoginETPassworde.getText().toString();
                    if (HelperMethods.checkConnection(getActivity())) {
                        login(phoneNo, password);
                    } else {
                        Toast.makeText(getContext(), getString(R.string.Toast_No_Internet), Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.Login_B_Reg:
                Toast.makeText(getContext(), getString(R.string.Toast_Register), Toast.LENGTH_LONG).show();
                //replace with register fragment
                break;
        }
    }

    private void login(String phoneNo, String password) {

     /*   apiServeces.Login(new LoginData(phoneNo, password)).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

              // if (response.body().get(0).getStatus() == 1) {
                //    Toast.makeText(getContext(), response.body().get(0).getMessage(), Toast.LENGTH_LONG).show();
                  //  startActivity(new Intent(getActivity(), ResturantListActivity.class));
                //} else {
                  //  Toast.makeText(getContext(), response.body().get(0).getMessage(), Toast.LENGTH_LONG).show();
                //}
                Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        */
    }
}
