package com.sherlocked.buildabit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity {

    public void openVogue(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","Vogue");
        startActivity(intent);
    }
    public void openElle(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","Elle");
        startActivity(intent);
    }
    public void openBazzar(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","Harbour");
        startActivity(intent);
    }
    public void openCosmo(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","Cosmo");
        startActivity(intent);
    }
    public void openBOF(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","BOF");
        startActivity(intent);
    }
    public void openW(android.view.View view){
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        intent.putExtra("Site","W");
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}
