package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        userNameText = findViewById(R.id.textView2);  // Ensure this ID is in your XML

        // Get logged-in user's name
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userNameText.setText("Hi, " + user.getDisplayName());  // Display user name
        }

        // Finding CardViews
        CardView generalDentistry = findViewById(R.id.cardGeneralDentistry);
        CardView cosmeticDentistry = findViewById(R.id.cardCosmeticDentistry);
        CardView orthodontics = findViewById(R.id.cardOrthodontics);
        CardView oralSurgery = findViewById(R.id.cardOralSurgery);
        CardView pediatricDentistry = findViewById(R.id.cardPediatricDentistry);
        CardView periodontics = findViewById(R.id.cardPeriodontics);
        CardView restorativeDentistry = findViewById(R.id.cardRestorativeDentistry);
        CardView emergencyDentalCare = findViewById(R.id.cardEmergencyDentalCare);

        // Setting Click Listeners
        generalDentistry.setOnClickListener(view -> openTreatmentPage(GeneralDentistryActivity.class));
        cosmeticDentistry.setOnClickListener(view -> openTreatmentPage(CosmeticDentistryActivity.class));
        orthodontics.setOnClickListener(view -> openTreatmentPage(OrthodonticsActivity.class));
        oralSurgery.setOnClickListener(view -> openTreatmentPage(OralSurgeryActivity.class));
        pediatricDentistry.setOnClickListener(view -> openTreatmentPage(PediatricDentistryActivity.class));
        periodontics.setOnClickListener(view -> openTreatmentPage(PeriodonticsActivity.class));
        restorativeDentistry.setOnClickListener(view -> openTreatmentPage(RestorativeDentistryActivity.class));
        emergencyDentalCare.setOnClickListener(view -> openTreatmentPage(EmergencyDentalCareActivity.class));
    }

    // Method to open treatment activity
    private void openTreatmentPage(Class<?> activityClass) {
        Intent intent = new Intent(DashBoardActivity.this, activityClass);
        startActivity(intent);
    }
}

