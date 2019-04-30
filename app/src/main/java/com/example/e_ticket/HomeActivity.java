package com.example.e_ticket;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private RecyclerView.Adapter<MyViewHolde> adapter;

    private ArrayList<MyData> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        dataset = new ArrayList<>();

        for (int i = 0; i <1000; i++) {

        }

        rcv = new RecyclerView(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerView.Adapter<MyViewHolde>() {

            //4. เติมเต็น adapter
            @Override
            public MyViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View card = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_home, parent, false);
                return new MyViewHolde(card);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolde myViewHolde, int position) {
                myViewHolde.tvNumber.setText("" + position);
                if (myViewHolde.tvNumber.getTag() == null) {
                    myViewHolde.tvNumber.setTag("" + position);
                    Log.i("abc", "View Number : " + position + " " + myViewHolde.tvNumber.getText().toString());
                } else {
                    Log.i("abc", "View Number : " + myViewHolde.tvNumber.getTag() + " " + myViewHolde.tvNumber.getText().toString());
                }
            }

            @Override
            public int getItemCount() {
                return 1000;
            }
        }; //2. ต้องการ class viewHolder
        rcv.setAdapter(adapter); //1.ต้องส้ราง adapter
        setContentView(rcv);

    }

    //3.สร้าง viewHolder
    private class MyViewHolde extends RecyclerView.ViewHolder {
        TextView tvNumber;

        public MyViewHolde(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }

    class MyData {
        int pic;
        String title;

    }
}