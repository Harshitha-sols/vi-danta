package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PediatricDentistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pediatricdentistry); // Ensure this matches your XML filename

        // Finding Treatment CardViews
        CardView kidsDentalCheckups = findViewById(R.id.cardKidsDentalCheckups);
        CardView fluorideSealants = findViewById(R.id.cardFluorideSealants);
        CardView spaceMaintainers = findViewById(R.id.cardSpaceMaintainers);

        // Setting Click Listeners
        kidsDentalCheckups.setOnClickListener(view -> openBookingActivity("Kids' Dental Checkups"));
        fluorideSealants.setOnClickListener(view -> openBookingActivity("Fluoride & Sealants"));
        spaceMaintainers.setOnClickListener(view -> openBookingActivity("Space Maintainers"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(PediatricDentistryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

