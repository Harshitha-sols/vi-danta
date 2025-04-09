package com.danta1.vdanta;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class BookingActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner spinnerTimeSlots;
    private Button btnConfirm;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        datePicker = findViewById(R.id.datePicker);
        spinnerTimeSlots = findViewById(R.id.spinnerTimeSlots);
        btnConfirm = findViewById(R.id.btnConfirm);
        db = new DatabaseHelper(this);

        // Restrict past dates
        Calendar calendar = Calendar.getInstance();
        datePicker.setMinDate(calendar.getTimeInMillis());

        // Set time slots
        String[] timeSlots = {"10:00 AM", "11:00 AM", "12:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeSlots);
        spinnerTimeSlots.setAdapter(adapter);

        btnConfirm.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            String time = spinnerTimeSlots.getSelectedItem().toString();
            String date = day + "/" + month + "/" + year;

            boolean inserted = db.insertAppointment(date, time);
            if (inserted) {
                Toast.makeText(this, "Appointment Confirmed for " + date + " at " + time, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to confirm appointment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


