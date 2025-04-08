package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CosmeticDentistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmeticdentistry);

        // Finding Treatment CardViews
        CardView whitening = findViewById(R.id.cardWhitening);
        CardView veneers = findViewById(R.id.cardVeneers);
        CardView bonding = findViewById(R.id.cardDentalBonding);
        CardView smileMakeover = findViewById(R.id.cardSmileMakeover);

        // Setting Click Listeners
        whitening.setOnClickListener(view -> openBookingActivity("Teeth Whitening"));
        veneers.setOnClickListener(view -> openBookingActivity("Veneers"));
        bonding.setOnClickListener(view -> openBookingActivity("Dental Bonding"));
        smileMakeover.setOnClickListener(view -> openBookingActivity("Smile Makeover"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(CosmeticDentistryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

