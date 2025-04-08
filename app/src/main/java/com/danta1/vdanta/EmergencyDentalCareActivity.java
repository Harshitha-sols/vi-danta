package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EmergencyDentalCareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencydentalcare); // Ensure this matches your XML filename

        // Finding Treatment CardViews
        CardView brokenTooth = findViewById(R.id.cardBrokenTooth);
        CardView dentalAbscess = findViewById(R.id.cardDentalAbscess);
        CardView knockedOutTooth = findViewById(R.id.cardKnockedOut);

        // Setting Click Listeners
        brokenTooth.setOnClickListener(view -> openBookingActivity("Broken Tooth Repair"));
        dentalAbscess.setOnClickListener(view -> openBookingActivity("Dental Abscess Treatment"));
        knockedOutTooth.setOnClickListener(view -> openBookingActivity("Knocked-out Tooth Care"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(EmergencyDentalCareActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

