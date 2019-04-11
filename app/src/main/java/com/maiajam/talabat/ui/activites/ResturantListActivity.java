package com.maiajam.talabat.ui.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.maiajam.talabat.R;
import com.maiajam.talabat.adapters.RestListAdapter;
import com.maiajam.talabat.data.TalabatApi.retrofit.ApiServeces;
import com.maiajam.talabat.data.models.getReatrurant.GetRestReturn;
import com.maiajam.talabat.data.models.getReatrurant.GetResturent;
import com.maiajam.talabat.helpers.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.talabat.data.TalabatApi.retrofit.ApiServer.getClient;

public class ResturantListActivity extends AppCompatActivity {

    @BindView(R.id.ResturantList_RecyView)
    RecyclerView ResturantListRecyView;
    private ApiServeces apiServeces;
    private List<GetRestReturn> RestList;
    private RestListAdapter adapter;
    private LinearLayoutManager layoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_list);
        ButterKnife.bind(this);
        apiServeces = getClient().create(ApiServeces.class);
        setList();
    }

    private void setList() {
        getRestList();
    }

    private void getRestList() {

        RestList = new ArrayList<>();
        if (HelperMethods.checkConnection(getBaseContext())) {
            apiServeces.getRestList("ar").enqueue(new Callback<GetResturent>() {
                @Override
                public void onResponse(Call<GetResturent> call, Response<GetResturent> response) {
                    if (response.body().getStatus() == 403) {

                        Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        RestList = response.body().getReturn();
                        adapter = new RestListAdapter(getBaseContext(), RestList);
                        layoutManger = new LinearLayoutManager(getBaseContext());
                        ResturantListRecyView.setAdapter(adapter);
                        ResturantListRecyView.setLayoutManager(layoutManger);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<GetResturent> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.Toast_No_Internet), Toast.LENGTH_LONG).show();
        }    }

}
