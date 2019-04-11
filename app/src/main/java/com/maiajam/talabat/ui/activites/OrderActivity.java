package com.maiajam.talabat.ui.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.talabat.R;
import com.maiajam.talabat.adapters.OderListAdapter;
import com.maiajam.talabat.data.TalabatApi.retrofit.ApiServeces;
import com.maiajam.talabat.data.models.getOrder.GetOrder;
import com.maiajam.talabat.data.models.getOrder.OrderDetail;
import com.maiajam.talabat.helpers.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.talabat.data.TalabatApi.retrofit.ApiServer.getClient;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.Order_RecView)
    RecyclerView OrderRecView;

    private String orderId;
    private ApiServeces apiService;
    private String restName;
    private List<OrderDetail> orderDetalis;
    private OderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        apiService = getClient().create(ApiServeces.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            orderId = bundle.getString(getString(R.string.Extra_OrderId));
            restName = bundle.getString(getString(R.string.Extra_ResName));
        }
        if (HelperMethods.checkConnection(getBaseContext())) {
            getOrderDatails(orderId);
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.Toast_No_Internet), Toast.LENGTH_LONG).show();
        }
    }

    private void getOrderDatails(final String orderId) {
        apiService.getOrder(restName, "ar").enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {

                if (response.body().getStatus() == 403) {
                    Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    orderDetalis = response.body().getReturn().get(Integer.valueOf(orderId)).getOrderDetails();
                    setOrders(orderDetalis);
                }

            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {

                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setOrders(List<OrderDetail> orderDetalis) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        adapter = new OderListAdapter(getBaseContext(), orderDetalis);
        OrderRecView.setLayoutManager(layoutManager);
        OrderRecView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
