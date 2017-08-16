package com.example.mufasa.j_max;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


//delays the home activity for 4 seconds as it loads the splash screen.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(getApplicationContext(),home.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}
