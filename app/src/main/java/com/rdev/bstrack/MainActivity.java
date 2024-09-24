package com.rdev.bstrack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdev.bstrack.databinding.ActivityMainBinding;
import com.rdev.bstrack.fragments.BusPass;
import com.rdev.bstrack.fragments.LocateBus;
import com.rdev.bstrack.fragments.Complaint;
import com.rdev.bstrack.service.LocationService;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION_PERMISSIONS = 1;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.login_page);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new BusPass());// Default fragment module or screen ranjit


        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.locate_bus){
                topAppBar.setTitle(R.string.app_name);
                replaceFragment(new LocateBus());
            }else if(itemId == R.id.bus_pass ){
                topAppBar.setTitle("Bus Pass");
                replaceFragment(new BusPass());
            }else if(itemId == R.id.user_complaints ){
                topAppBar.setTitle("Complaints");
                replaceFragment(new Complaint());
            }
            return true;
        });

        if (!hasLocationPermissions()) {
            requestLocationPermissions();
        } else {
            startLocationService();
        }
    }


    private boolean hasLocationPermissions() {
        boolean foregroundLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean backgroundLocation = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            backgroundLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }

        return foregroundLocation && backgroundLocation;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void requestLocationPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show a message explaining why you need the location permissions
            // Optionally, show a dialog and proceed with requesting permissions after the user understands the need.
        }

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_LOCATION_PERMISSIONS);
    }

    private void startLocationService() {
        Intent serviceIntent = new Intent(this, LocationService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Request background location separately for Android Q and above
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_PERMISSIONS);
                } else {
                    startLocationService();
                }

            } else {
                // Handle the case where permissions are denied
                // Optionally, direct the user to settings or explain why permissions are necessary
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
