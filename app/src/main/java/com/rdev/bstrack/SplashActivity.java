package com.rdev.bstrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rdev.bstrack.MainActivity;
import com.rdev.bstrack.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add a splash screen delay (optional)
        new Handler().postDelayed(() -> {
            // Check if the user is logged in
            Boolean currentUser =true;

            if (currentUser != null) {
                // User is logged in, navigate to HomeActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // User is not logged in, navigate to LoginActivity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish(); // Close SplashActivity
        }, 2000); // Optional 2-second delay for splash screen
    }
}
