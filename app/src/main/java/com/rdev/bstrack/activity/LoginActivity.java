package com.rdev.bstrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rdev.bstrack.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailInputLayout, passwordInputLayout;
    private TextInputEditText emailEditText, passwordEditText;
    private TextView signUpLink;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);
        signUpLink = findViewById(R.id.signup);

        // Set Button Click Listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterOneActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        // Get Input Values
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate Inputs
        if (TextUtils.isEmpty(email)) {
            emailInputLayout.setError("Email is required");
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.setError("Invalid email address");
            return;
        } else {
            emailInputLayout.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordInputLayout.setError("Password is required");
            return;
        } else if (password.length() < 6) {
            passwordInputLayout.setError("Password must be at least 6 characters");
            return;
        } else {
            passwordInputLayout.setError(null);
        }

        // If inputs are valid
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

        // Perform login logic (e.g., authenticate with a server)
    }
}
