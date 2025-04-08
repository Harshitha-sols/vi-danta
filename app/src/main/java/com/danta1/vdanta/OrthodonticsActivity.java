package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OrthodonticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orthodontics); // Ensure this matches your XML filename

        // Finding Treatment CardViews
        CardView metalBraces = findViewById(R.id.cardMetalBraces);
        CardView clearAligners = findViewById(R.id.cardClearAligners);
        CardView retainers = findViewById(R.id.cardRetainers);

        // Setting Click Listeners
        metalBraces.setOnClickListener(view -> openBookingActivity("Metal Braces"));
        clearAligners.setOnClickListener(view -> openBookingActivity("Clear Aligners"));
        retainers.setOnClickListener(view -> openBookingActivity("Retainers"));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(OrthodonticsActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

