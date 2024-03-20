package com.example.apifetch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList();

        RecyclerView rsv = findViewById(R.id.rsv);
        rsv.setLayoutManager(new GridLayoutManager(this,2));
        rsv.setHasFixedSize(true);
        ListAdapter adapter = new ListAdapter(list,this);
        rsv.setAdapter(adapter);

        new Thread(() -> {
            Log.e("Thead","Inside thread");
            new ApiCall().service.getAllData().enqueue(new Callback<List<Model>>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Response<List<Model>> response, Retrofit retrofit) {
                    if (!response.isSuccess()) {
                        Log.e("failure", "Unsuccessful");
                    }
                    if (response.isSuccess()) {
                        Log.d("Success", "successful");
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("Fail", t.getMessage());

                }
            });
        }).start();

    }
}