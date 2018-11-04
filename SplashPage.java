package com.example.dell.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Button bowner, badmin,baccount;

        bowner=(Button)findViewById(R.id.owner);
        bowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashPage.this, Login_owner.class));
            }
        });


        badmin=(Button)findViewById(R.id.admin);
        badmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashPage.this, loginActivity.class));
            }
        });

        baccount=(Button)findViewById(R.id.accountant);
        baccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashPage.this,Login_accountant.class));

            }
        });
    }
}
