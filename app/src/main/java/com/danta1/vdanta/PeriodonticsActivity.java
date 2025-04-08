package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PeriodonticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.periodontics); // Ensure this matches your XML filename

        // Finding Treatment CardViews
        CardView gumDisease = findViewById(R.id.cardGumDisease);
        CardView rootPlaning = findViewById(R.id.cardRootPlanting);
        CardView laserGum = findViewById(R.id.cardLaserGum);

        // Setting Click Listeners
        gumDisease.setOnClickListener(view -> openBookingActivity("Gum Disease Treatment"));
        rootPlaning.setOnClickListener(view -> openBookingActivity("Scaling & Root Planing"));
        laserGum.setOnClickListener(view -> openBookingActivity("Laser Gum Therapy"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(PeriodonticsActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

