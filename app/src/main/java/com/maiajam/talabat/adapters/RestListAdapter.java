package com.maiajam.talabat.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.maiajam.talabat.data.models.getReatrurant.GetRestReturn;
import com.maiajam.talabat.helpers.Constant;
import com.maiajam.talabat.ui.activites.OrderActivity;
import com.maiajam.talabat.ui.activites.RestMapsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestListAdapter extends RecyclerView.Adapter<RestListAdapter.Holder> {

    private final Context mContext;
    private final List<GetRestReturn> mRestList;
    @BindView(R.id.itemRestList_ImgV_RestPhoto)
    ImageView itemRestListImgVRestPhoto;
    @BindView(R.id.itemRestList_TV_RestName)
    TextView itemRestListTVRestName;
    @BindView(R.id.itemRestList_TV_RestType)
    TextView itemRestListTVRestType;
    @BindView(R.id.itemRestList_ImgV_ResLocation)
    ImageView itemRestListImgVResLocation;
    @BindView(R.id.itemRestList_CardView)
    CardView itemRestListCardView;


    public RestListAdapter(Context baseContext, List<GetRestReturn> restList) {
        mContext = baseContext;
        mRestList = restList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_restlist, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final GetRestReturn mRestData = mRestList.get(position);

        holder.itemRestListTVRestName.setText(mRestData.getRestName());
        holder.itemRestListTVRestType.setText(mRestData.getRestType());

        Glide.with(mContext).load(mRestData.getRestImg()).apply(new RequestOptions().
                override(Constant.RESTPHOTO_WIDTH, Constant.RESTPHOTO_HIGHT).centerCrop()).
                into(holder.itemRestListImgVRestPhoto);

        holder.itemRestListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, RestMapsActivity.class)
                        .putExtra(mContext.getString(R.string.Extra_ResId), mRestData.getRestId())
                        .putExtra(mContext.getString(R.string.Extra_ResLocation), mRestData.getRestLocation())
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemRestList_ImgV_RestPhoto)
        ImageView itemRestListImgVRestPhoto;
        @BindView(R.id.itemRestList_TV_RestName)
        TextView itemRestListTVRestName;
        @BindView(R.id.itemRestList_TV_RestType)
        TextView itemRestListTVRestType;
        @BindView(R.id.itemRestList_ImgV_ResLocation)
        ImageView itemRestListImgVResLocation;
        @BindView(R.id.itemRestList_CardView)
        CardView itemRestListCardView;


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
