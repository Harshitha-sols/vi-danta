package com.danta1.vdanta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        TextView textCreateAccount = findViewById(R.id.textCreateAccount);
        TextView textForgotPassword = findViewById(R.id.textForgotPassword);

        buttonLogin.setOnClickListener(v -> loginUser());

        textCreateAccount.setOnClickListener(v ->
                startActivity(new Intent(SignInActivity.this, SignupActivity.class))
        );

        textForgotPassword.setOnClickListener(v ->
                Toast.makeText(this, "Password reset feature coming soon!", Toast.LENGTH_SHORT).show()
        );
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!isValidGmail(email)) {
            etEmail.setError("Enter a valid Gmail address (@gmail.com)");
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return;
        }

        // Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(SignInActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, DashBoardActivity.class));
                            finish(); // Close SignInActivity
                        }
                    } else {
                        Toast.makeText(SignInActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isValidGmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com");
    }
}

