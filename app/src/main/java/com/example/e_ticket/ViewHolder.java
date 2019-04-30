package com.example.e_ticket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

class ViewHolder extends RecyclerView.ViewHolder {

    private View mView;

    ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        ////////////////////////////////////////////////////////////////////////
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
        ///////////////////////////////////////////////////////////////////////////
    }

    // Set details to recycler view row
    void setDetails(String title, String description, String image) {
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageTv = mView.findViewById(R.id.rImageView);

        //set data view
        mTitleTv.setText(title);
        mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageTv);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public  void  setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
}
