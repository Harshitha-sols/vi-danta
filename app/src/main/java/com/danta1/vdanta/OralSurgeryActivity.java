package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OralSurgeryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oralsurgery); // Ensure this matches your XML filename

        // Finding Treatment CardViews
        CardView wisdomTooth = findViewById(R.id.cardWisdomTooth);
        CardView jawSurgery = findViewById(R.id.cardJawSurgery);
        CardView boneGrafting = findViewById(R.id.cardBoneGrafting);

        // Setting Click Listeners
        wisdomTooth.setOnClickListener(view -> openBookingActivity(getString(R.string.wisdom_tooth)));
        jawSurgery.setOnClickListener(view -> openBookingActivity(getString(R.string.jaw_surgery)));
        boneGrafting.setOnClickListener(view -> openBookingActivity(getString(R.string.bone_grafting)));
    }

    // Open BookingActivity with selected treatment name
    private void openBookingActivity(String treatmentName) {
        Intent intent = new Intent(OralSurgeryActivity.this, BookingActivity.class);
        intent.putExtra("TREATMENT_NAME", treatmentName);
        startActivity(intent);
    }
}

