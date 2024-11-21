package com.rdev.bstrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rdev.bstrack.activity.LoginActivity;
import com.rdev.bstrack.databinding.ActivityMainBinding;
import com.rdev.bstrack.fragments.LocateBus;
import com.rdev.bstrack.sheets.AboutSheet;
import com.rdev.bstrack.sheets.ProfileSheet;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION_PERMISSIONS = 1;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onRestart() {
        super.onRestart();
//        setContentView(R.layout.login_page);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        BottomAppBar bottomAppBar = findViewById(R.id.my_bottom_app_bar);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        replaceFragment(new LocateBus());   // Default fragment module or screen ranjit



        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.account){
                ProfileSheet pf = new ProfileSheet();
                pf.show(getSupportFragmentManager(),pf.getTag());
            }else if(itemId == R.id.about ){
                AboutSheet as = new AboutSheet();
                as.show(getSupportFragmentManager(),as.getTag());
            }else if(itemId == R.id.user_complaints ){

            }
            return true;
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out the user
//                FirebaseAuth.getInstance().signOut();

                // Redirect to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close HomeActivity
            }
        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
