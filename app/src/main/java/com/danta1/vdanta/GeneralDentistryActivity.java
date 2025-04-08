package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GeneralDentistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generaldentistry);

        // Finding Treatment CardViews
        CardView checkups = findViewById(R.id.cardCheckups);
        CardView toothExtraction = findViewById(R.id.cardToothExtraction);
        CardView fillings = findViewById(R.id.cardFillingsAndSealants);
        CardView fluoride = findViewById(R.id.cardFluorideTreatment);
        CardView scaling = findViewById(R.id.cardScalingAndPolishing);

        // Setting Click Listeners
        checkups.setOnClickListener(view -> openBookingActivity("Checkups and Cleaning"));
        toothExtraction.setOnClickListener(view -> openBookingActivity("Tooth Extraction"));
        fillings.setOnClickListener(view -> openBookingActivity("Fillings and Sealants"));
        fluoride.setOnClickListener(view -> openBookingActivity("Fluoride Treatment"));
        scaling.setOnClickListener(view -> openBookingActivity("Scaling and Polishing"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(GeneralDentistryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}



