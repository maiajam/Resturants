package com.maiajam.talabat.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maiajam.talabat.R;
import com.maiajam.talabat.data.models.getOrder.OrderDetail;
import com.maiajam.talabat.data.models.getReatrurant.GetRestReturn;
import com.maiajam.talabat.helpers.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OderListAdapter extends RecyclerView.Adapter<OderListAdapter.ViewHolder> {


    private final Context mContext;
    private final List<OrderDetail> orderList;

    public OderListAdapter(Context baseContext, List<OrderDetail> OrderList) {

        mContext = baseContext ;
        orderList = OrderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ordertlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderDetail orderDetail = orderList.get(position);

        holder.itemOrderListTVOrderName.setText(orderDetail.getProdName());
        holder.itemOrderListTVPrderPrice.setText(orderDetail.getProdPrice());

        Glide.with(mContext).load(orderDetail.getProdImage()).apply(new RequestOptions().
                override(Constant.RESTPHOTO_WIDTH, Constant.RESTPHOTO_HIGHT).centerCrop()).
                into(holder.itemOrderListImgVOrderPhoto);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemOrderList_ImgV_OrderPhoto)
        ImageView itemOrderListImgVOrderPhoto;
        @BindView(R.id.itemOrderList_TV_OrderName)
        TextView itemOrderListTVOrderName;
        @BindView(R.id.itemOrderList_TV_PrderPrice)
        TextView itemOrderListTVPrderPrice;
        @BindView(R.id.itemOrderList_CardView)
        CardView itemOrderListCardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
