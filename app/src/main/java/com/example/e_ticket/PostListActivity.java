package com.example.e_ticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class PostListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

//        //Actionbar
//        ActionBar actionBar = getSupportActionBar();
//        //set title
//        actionBar.setTitle("Posts List");

        // ReceyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send Query to Firebase Database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("Data");

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(mRef, Model.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row, parent, false);

                ///////////////////////ที่ทำใหม่//////////////////////////

                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//                ViewHolder viewHolder = onCreateViewHolder(parent,viewType);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //view
                        TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        ImageView mImageView = view.findViewById(R.id.rImageView);
                        //get data from views
                        String mTitle = mTitleTv.getText().toString();
                        String mDesc = mDescTv.getText().toString();
                        Drawable mDrawable = mImageView.getDrawable();
                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] bytes = stream.toByteArray();
                        intent.putExtra("image", bytes); //put bitmap image as array of bytes
                        intent.putExtra("title", mTitle); //put tile
                        intent.putExtra("description", mDesc); //put description
                        startActivity(intent); //start activity

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        //TOD do your own implementtaion on long item click
                    }
                });

                ///////////////////////////////////////////////
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                Log.d("BIND", model.getTitle());
                holder.setDetails(model.getTitle(), model.getDescription(), model.getImage());
            }
        };

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

    //search
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//
//        searchView.setOnQueryTextListener(
//                new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String s) {
////                        adapter.getFiler().filter(s);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String s) {
//
//                        return false;
//                    }
//                });
//
//        return true;
//    }
//}

    //search data
//    private void firebaseSearch(String searchText) {
//        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(searchText).endAt(searchText + "uf88f");
//
//        FirebaseRecyclerOptions<Model> soptions =
//                new FirebaseRecyclerOptions.Builder<Model>()
//                        .setQuery(mRef, Model.class)
//                        .build();
//
//        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(soptions) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
//                        Log.d("BINDAA", model.getTitle());
//                        holder.setDetails(model.getTitle(), model.getDescription(), model.getImage());
//                    }
//
//                    @NonNull
//                    @Override
//                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                        return null;
//                    }
//                };
//        mRecyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu2, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                firebaseSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                firebaseSearch(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //handle other action bar item clicks here
//        if (id == R.id.action_settings) {
//            //TODO
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}


    //load data into recycler view onStart
//
//        @Override
//        protected void onStart() {
//            super.onStart();
//
//            FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
//                    new FirebaseRecyclerAdapter<Model, ViewHolder>(
//                            Model.class,
//                            R.layout.row,
//                            ViewHolder.class,
//                            mRef
//                    ) {
//                        @Override
//                        protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
//                            holder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage());
//                        }
//
//                        @NonNull
//                        @Override
//                        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                            return null;
//                        }
//                    };
//            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//        }

