package com.danta1.vdanta;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.danta1.vdanta.databinding.Welcome1Binding; // Ensure correct binding import

public class Welcome1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding with correct layout binding name
        Welcome1Binding binding = Welcome1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle Register Button Click
        binding.buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(Welcome1.this, SignupActivity.class); // Updated class name
            startActivity(intent);
        });

        // Handle Login Button Click
        binding.buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Welcome1.this, SignInActivity.class); // Updated class name
            startActivity(intent);
        });

    }
}



