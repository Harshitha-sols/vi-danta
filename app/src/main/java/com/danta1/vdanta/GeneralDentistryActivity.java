package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GeneralDentistryActivity extends AppCompatActivity {

    private static final String TAG = "GDA";  // short tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generaldentistry);

        // Find CardViews
        CardView checkups = findViewById(R.id.cardCheckups);
        CardView toothExtraction = findViewById(R.id.cardToothExtraction);
        CardView fillings = findViewById(R.id.cardFillingsAndSealants);
        CardView fluoride = findViewById(R.id.cardFluorideTreatment);
        CardView scaling = findViewById(R.id.cardScalingAndPolishing);

        // Add Click Listeners
        checkups.setOnClickListener(view -> {
            Toast.makeText(this, "Checkups Clicked", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Checkups CardView clicked");
            openBookingActivity("Checkups and Cleaning");
        });

        toothExtraction.setOnClickListener(view -> openBookingActivity("Tooth Extraction"));
        fillings.setOnClickListener(view -> openBookingActivity("Fillings and Sealants"));
        fluoride.setOnClickListener(view -> openBookingActivity("Fluoride Treatment"));
        scaling.setOnClickListener(view -> openBookingActivity("Scaling and Polishing"));
    }

    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(GeneralDentistryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}




