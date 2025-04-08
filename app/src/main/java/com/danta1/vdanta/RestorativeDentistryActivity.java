package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RestorativeDentistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restorativedentistry); // Ensure your XML file is named `restorativedentistry.xml`

        // Finding Treatment CardViews
        CardView dentalImplants = findViewById(R.id.cardDentalImplants);
        CardView crowns = findViewById(R.id.cardCrowns);
        CardView dentures = findViewById(R.id.cardDentures);
        CardView rootCanal = findViewById(R.id.cardRootCanal);


        // Setting Click Listeners
        dentalImplants.setOnClickListener(view -> openBookingActivity("dental_implants"));
        crowns.setOnClickListener(view -> openBookingActivity("crowns_bridges"));
        dentures.setOnClickListener(view -> openBookingActivity("dentures"));
        rootCanal.setOnClickListener(view -> openBookingActivity("root_canal"));

    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(RestorativeDentistryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

