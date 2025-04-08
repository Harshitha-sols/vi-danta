package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome); // Ensure XML name matches

        // Delay for 2 seconds before switching to Welcome1
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Welcome.this, Welcome1.class);
            startActivity(intent);
            finish(); // Close Welcome activity
        }, 2000); // 2000ms = 2 seconds
    }
}


