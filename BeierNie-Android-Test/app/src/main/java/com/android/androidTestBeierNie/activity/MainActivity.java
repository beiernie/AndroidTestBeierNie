package com.android.androidTestBeierNie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.androidTestBeierNie.R;
import com.android.androidTestBeierNie.adapter.CardAdapter;
import com.android.androidTestBeierNie.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.action_pairing:
                        startActivity(new Intent(MainActivity.this, EmptyActivity.class));
                        break;
                    case R.id.action_cellar:
                        startActivity(new Intent(MainActivity.this, EmptyActivity.class));
                        break;
                    case R.id.action_account:
                        startActivity(new Intent(MainActivity.this, EmptyActivity.class));
                        break;
                }
                return true;
            }
        });
    }



    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        productArrayList = new ArrayList<>();
        adapter = new CardAdapter(this, productArrayList);
        recyclerView.setAdapter(adapter);
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        createListData();
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void createListData() {

        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < jArray.length(); ++i) {
                String title = jArray.getJSONObject(i).getString("title");
                String imagePath = jArray.getJSONObject(i).getString("imagePath");
                JSONObject buyNow = jArray.getJSONObject(i).getJSONObject("buyNow");
                String unitPrice = buyNow.getString("unitPrice");
                Product prod = new Product();
                prod.setImageUrl(imagePath);
                String[] tempArr = title.split(",");
                prod.setOrigin(tempArr[1]);
                prod.setTitle(tempArr[0]);
                prod.setUnitPrice("AU $"+unitPrice);
                productArrayList.add(prod);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
