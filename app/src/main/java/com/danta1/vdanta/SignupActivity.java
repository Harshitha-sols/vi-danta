package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etPhone, etEmail, etPassword;
    private Button buttonCreateAccount;
    private ProgressBar progressBar;
    private TextView textAlreadyExists, textSignIn;
    private DatabaseHelper dbHelper;  // SQLite helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup); // Keep using your original signup.xml

        // Initialize UI
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        progressBar = findViewById(R.id.progressBar);
        textAlreadyExists = findViewById(R.id.textAlreadyExists);
        textSignIn = findViewById(R.id.textSignIn);

        // Initial state
        textAlreadyExists.setVisibility(View.GONE);
        textSignIn.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        dbHelper = new DatabaseHelper(this);  // Initialize DB

        buttonCreateAccount.setOnClickListener(v -> registerUser());

        textSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validation
        if (name.isEmpty()) {
            etName.setError("Name is required");
            return;
        }

        if (!isValidIndianMobileNumber(phone)) {
            etPhone.setError("Enter a valid Indian mobile number (+91 9876543210)");
            return;
        }

        if (!isValidGmail(email)) {
            etEmail.setError("Enter a valid Gmail address (@gmail.com)");
            return;
        }

        if (!isValidPassword(password)) {
            etPassword.setError("Password must have Uppercase, Lowercase, Number, Special Character (!@#$%^&*)");
            return;
        }

        // Show progress
        progressBar.setVisibility(View.VISIBLE);
        buttonCreateAccount.setEnabled(false);

        // Check if user already exists
        if (dbHelper.checkUserExists(email)) {
            progressBar.setVisibility(View.GONE);
            buttonCreateAccount.setEnabled(true);
            textAlreadyExists.setVisibility(View.VISIBLE);
            textSignIn.setVisibility(View.VISIBLE);
        } else {
            // Insert into DB
            boolean inserted = dbHelper.insertUser(name, phone, email, password);
            progressBar.setVisibility(View.GONE);
            buttonCreateAccount.setEnabled(true);
            if (inserted) {
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                // Optionally, redirect to SignInActivity
                startActivity(new Intent(SignupActivity.this, SignInActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidIndianMobileNumber(String phone) {
        return phone.matches("^\\+91[6-9]\\d{9}$");
    }

    private boolean isValidGmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com");
    }

    private boolean isValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$");
        return passwordPattern.matcher(password).matches();
    }
}

