package com.android.androidTestBeierNie.activity;

import android.content.Intent;
import android.os.Bundle;
import com.android.androidTestBeierNie.R;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class EmptyActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(EmptyActivity.this, MainActivity.class));
                        break;
                    case R.id.action_pairing:
                        startActivity(new Intent(EmptyActivity.this, EmptyActivity.class));
                        break;
                    case R.id.action_cellar:
                        startActivity(new Intent(EmptyActivity.this, EmptyActivity.class));
                        break;
                    case R.id.action_account:
                        startActivity(new Intent(EmptyActivity.this, EmptyActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}
