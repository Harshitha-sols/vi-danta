package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button buttonLogin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        TextView textCreateAccount = findViewById(R.id.textCreateAccount);
        TextView textForgotPassword = findViewById(R.id.textForgotPassword);
        db = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(v -> loginUser());

        textCreateAccount.setOnClickListener(v ->
                startActivity(new Intent(SignInActivity.this, SignupActivity.class))
        );

        textForgotPassword.setOnClickListener(v ->
                Toast.makeText(this, "Password reset coming soon!", Toast.LENGTH_SHORT).show()
        );
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Invalid email");
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return;
        }

        if (db.validateUser(email, password)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, DashBoardActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
        if (db.validateUser(email, password)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
            intent.putExtra("email", email);  // ✅ Pass the user's email
            startActivity(intent);
            finish();
        }
    }
}


