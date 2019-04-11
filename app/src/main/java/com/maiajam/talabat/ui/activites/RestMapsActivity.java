package com.maiajam.talabat.ui.activites;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maiajam.talabat.R;
import com.maiajam.talabat.data.TalabatApi.retrofit.ApiServeces;
import com.maiajam.talabat.data.models.getOrder.GetOrder;
import com.maiajam.talabat.data.models.getOrder.Return;
import com.maiajam.talabat.helpers.Constant;
import com.maiajam.talabat.helpers.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.talabat.data.TalabatApi.retrofit.ApiServer.getClient;

public class RestMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ApiServeces apiService;
    private String restlocation;
    private String restName;
    private List<Return> orderList;
    private AlertDialog dialog;
    private MarkerOptions marker;
    private Marker markerItem;
    private ArrayList<Return> orderListItems;
    private List<Marker> markerList;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_maps);
        apiService = getClient().create(ApiServeces.class);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            restlocation = extra.getString(getString(R.string.Extra_ResLocation));
            restName = extra.getString(getString(R.string.Extra_ResName));
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getOrders() {
        orderList = new ArrayList<>();
        apiService.getOrder(restName, "ar").enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {

                if (response.body().getStatus() == 403) {
                    Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    orderList = response.body().getReturn();
                    setlocation(orderList);
                }

            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {

                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (HelperMethods.checkConnection(getBaseContext())) {
                getOrders();
            } else {

            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    Constant.REQUEST_LOCATION_PERMISSION);
        }

    }

    private void setlocation(final List<Return> orderlist) {
        String location[] = restlocation.split(",");
        String lat = location[0];
        String lan = location[1];
        LatLng restLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lan));
        mMap.addMarker(new MarkerOptions().position(restLocation).title("location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restLocation, 16));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 5000, null);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        setMapLongClick(mMap);
        if (!orderList.isEmpty()) {
            for (int i = 0; i < orderList.size(); i++) {
                Return orderItem = orderList.get(i);
                String orderItemLocation = orderItem.getUserLocation();
                String Orderlocation[] = orderItemLocation.split(",");
                String Orderlat = Orderlocation[0];
                String Orderlan = Orderlocation[1];
                LatLng orderLoc = new LatLng(Double.parseDouble(Orderlat), Double.parseDouble(Orderlan));
                markerItem = mMap.addMarker(new MarkerOptions().position(orderLoc).title(orderList.get(i).getResturantName()));
                markerList.add(markerItem);
            }
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {

                for (int x = 0; x < orderList.size(); x++) {
                    String orderItemLocation = orderList.get(x).getUserLocation();
                    String Orderlocation[] = orderItemLocation.split(",");
                    String Orderlat = Orderlocation[0];
                    String Orderlan = Orderlocation[1];
                    LatLng orderLoc = new LatLng(Double.parseDouble(Orderlat), Double.parseDouble(Orderlan));
                    if (orderLoc.equals(latLng)) {
                        orderId = orderlist.get(x).getOrderId();
                    }
                }
                final AlertDialog.Builder d = new AlertDialog.Builder(getBaseContext());
                d.setItems(R.array.order_events_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                startActivity(new Intent(RestMapsActivity.this, OrderActivity.class)
                                        .putExtra(getString(R.string.Extra_OrderId), orderId)
                                        .putExtra(getString(R.string.Extra_ResName), restName));

                                break;
                            case 2:
                                viewDirction();
                                break;
                            case 3:
                                endTheOrder(latLng);
                                break;
                        }
                    }
                });
                dialog = d.create();
                dialog.show();
            }
        });
    }


    private void setMapLongClick(final GoogleMap map) {

    }

    private void endTheOrder(LatLng postion) {

        for (int x = 0; x < markerList.size(); x++) {
            if (markerList.get(x).getPosition().equals(postion)) {
                Marker marker = markerList.get(x);
                marker.remove();
            }
        }
    }

    private void viewDirction() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    Constant.REQUEST_LOCATION_PERMISSION);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case Constant.REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    getOrders();
                    break;
                }
        }
    }
}

