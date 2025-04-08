package com.danta1.vdanta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private TextView textTreatmentName;
    private Button datePickerButton, timePickerButton, btnConfirmBooking;
    private EditText editPhone, editNotes;

    private Calendar selectedDateTime = Calendar.getInstance();
    private String treatmentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        // Initialize views
        textTreatmentName = findViewById(R.id.textTreatmentName);
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        editPhone = findViewById(R.id.editPhone);
        editNotes = findViewById(R.id.editNotes);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        // Get treatment name from intent
        if (getIntent() != null && getIntent().hasExtra("TREATMENT_NAME")) {
            treatmentName = getIntent().getStringExtra("TREATMENT_NAME");
            textTreatmentName.setText(treatmentName);
        }

        // Set initial date and time
        updateDateButton();
        updateTimeButton();

        // Date picker
        datePickerButton.setOnClickListener(view -> {
            int year = selectedDateTime.get(Calendar.YEAR);
            int month = selectedDateTime.get(Calendar.MONTH);
            int day = selectedDateTime.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view1, year1, month1, dayOfMonth) -> {
                        selectedDateTime.set(year1, month1, dayOfMonth);
                        updateDateButton();
                    }, year, month, day);
            datePicker.show();
        });

        // Time picker
        timePickerButton.setOnClickListener(view -> {
            int hour = selectedDateTime.get(Calendar.HOUR_OF_DAY);
            int minute = selectedDateTime.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(this,
                    (view1, hourOfDay, minute1) -> {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute1);
                        updateTimeButton();
                    }, hour, minute, false);
            timePicker.show();
        });

        // Confirm booking
        btnConfirmBooking.setOnClickListener(view -> {
            String phone = editPhone.getText().toString().trim();
            String notes = editNotes.getText().toString().trim();

            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            // You can now save to Firebase here if needed
            String summary = "Appointment for " + treatmentName +
                    " on " + getFormattedDate() +
                    " at " + getFormattedTime() +
                    "\nPhone: " + phone +
                    (notes.isEmpty() ? "" : ("\nNotes: " + notes));

            Toast.makeText(this, "Booked:\n" + summary, Toast.LENGTH_LONG).show();

            // You can finish activity or navigate back
        });
    }

    private void updateDateButton() {
        datePickerButton.setText(getFormattedDate());
    }

    private void updateTimeButton() {
        timePickerButton.setText(getFormattedTime());
    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(selectedDateTime.getTime());
    }

    private String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(selectedDateTime.getTime());
    }
}

