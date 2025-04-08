package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etPhone, etEmail, etPassword;
    private Button buttonCreateAccount;
    private ProgressBar progressBar;
    private TextView textAlreadyExists, textSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        progressBar = findViewById(R.id.progressBar);
        textAlreadyExists = findViewById(R.id.textAlreadyExists);
        textSignIn = findViewById(R.id.textSignIn);

        textAlreadyExists.setVisibility(View.GONE);
        textSignIn.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

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

        progressBar.setVisibility(View.VISIBLE);
        buttonCreateAccount.setEnabled(false);

        // Simulate success for UI testing
        progressBar.setVisibility(View.GONE);
        Toast.makeText(SignupActivity.this, "All inputs are valid! UI looks good.", Toast.LENGTH_SHORT).show();
        buttonCreateAccount.setEnabled(true);
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
