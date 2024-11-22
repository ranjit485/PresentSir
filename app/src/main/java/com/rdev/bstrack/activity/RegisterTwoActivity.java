package com.rdev.bstrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rdev.bstrack.R;

public class RegisterTwoActivity extends AppCompatActivity {

    private TextInputLayout contactInputLayout, genderInputLayout, busInputLayout;
    private TextInputEditText contactEditText;
    private AutoCompleteTextView genderDropdown, busDropdown;
    private MaterialButton createAccountButton;
    private TextView loginText ,backToPrivious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_two);

        // Initialize Views
        contactInputLayout = findViewById(R.id.contactInputLayout);
        genderInputLayout = findViewById(R.id.genderInputLayout);
        busInputLayout = findViewById(R.id.busInputLayout);


        contactEditText = findViewById(R.id.contactEditText);

        genderDropdown = findViewById(R.id.genderDropdown);
        busDropdown = findViewById(R.id.busDropdown);

        createAccountButton = findViewById(R.id.createAccountButton);
        loginText = findViewById(R.id.loginTextView);
        backToPrivious = findViewById(R.id.backToStepOneButton);

        // Populate Dropdowns
        setupDropdowns();

        // Set Button Click Listeners
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTwoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        backToPrivious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupDropdowns() {
        // Gender Dropdown Options
        String[] genders = {"Male", "Female", "Other"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, genders);
        genderDropdown.setAdapter(genderAdapter);

        // Bus Dropdown Options
        String[] buses = {"Bus 1", "Bus 2", "Bus 3"};
        ArrayAdapter<String> busAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, buses);
        busDropdown.setAdapter(busAdapter);
    }

    private void createAccount() {
        // Get Input Values
        String contact = contactEditText.getText().toString().trim();
        String gender = genderDropdown.getText().toString().trim();
        String bus = busDropdown.getText().toString().trim();

        // Validate Input Fields
        if (TextUtils.isEmpty(contact)) {
            contactInputLayout.setError("Mobile number is required");
            return;
        } else {
            contactInputLayout.setError(null);
        }

        if (TextUtils.isEmpty(gender)) {
            genderInputLayout.setError("Please select a gender");
            return;
        } else {
            genderInputLayout.setError(null);
        }

        if (TextUtils.isEmpty(bus)) {
            busInputLayout.setError("Please select a bus");
            return;
        } else {
            busInputLayout.setError(null);
        }

        // If all inputs are valid, proceed with registration logic
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();

        // Example: Navigate to another activity or save the data
    }
}