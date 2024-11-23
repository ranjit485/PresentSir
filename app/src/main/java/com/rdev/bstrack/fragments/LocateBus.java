package com.rdev.bstrack.fragments;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.rdev.bstrack.R;
public class LocateBus extends Fragment {

    private static final int PERMISSION_REQUEST_LOCATION = 1001;

    private MapView mapView;
    private MapboxMap mapboxMap;
    private BroadcastReceiver locationReceiver;
    private boolean isCameraPositionUpdated = false;
    private Marker currentMarker;
    private double userLongitude;
    private double userLatitude;

    private FloatingActionButton userLocationButton;
    private FloatingActionButton busLocationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize Mapbox instance
        Mapbox.getInstance(requireContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the MapView
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        userLocationButton = view.findViewById(R.id.my_loc_button);
        busLocationButton = view.findViewById(R.id.bus_loc_button);

        // Set click listeners
        userLocationButton.setOnClickListener(v -> {
            updateMapWithLocation(new LatLng(userLatitude, userLongitude), true);

        });
        busLocationButton.setOnClickListener(v -> showBusLocation(17.239924, 74.771664));

        // Set up the map with MapTiler style
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                LocateBus.this.mapboxMap = mapboxMap;
                mapboxMap.setStyle(new Style.Builder().fromUri(getString(R.string.maptiler_style_url)), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        // Start the location service
                    }
                });
            }
        });

        // Register location receiver

        return view;
    }


    private void updateMapWithLocation(LatLng userLocation, boolean isCameraButton) {


        if (mapboxMap == null) return;

        // Remove the previous marker
        if (currentMarker != null) {
            mapboxMap.removeMarker(currentMarker);
        }

        // Add the new marker
        currentMarker = addMarker(userLocation, "User Location");

        // Provide user feedback
        showLocationUpdatedToast();

        if (isCameraButton){
            setCameraPosition(userLocation);
        }
        // Set the camera position to the user's location only once
        if (!isCameraPositionUpdated) {
            setCameraPosition(userLocation);
            isCameraPositionUpdated = true;
        }
    }

    private void showBusLocation(double busLatitude, double busLongitude) {
        // Add bus marker
        if (mapboxMap == null) return;

        // Remove the previous marker
        if (currentMarker != null) {
            mapboxMap.removeMarker(currentMarker);
        }

        currentMarker = addMarker(new LatLng(busLatitude, busLongitude), "Bus Location");

        // Provide user feedback with the distance
        float[] results = new float[1];
        android.location.Location.distanceBetween(userLatitude, userLongitude, busLatitude, busLongitude, results);
        showBusDistanceToast(results[0]);
    }

    private Marker addMarker(LatLng location, String title) {
        MarkerOptions markerOptions = new MarkerOptions()
                .setPosition(location)
                .setTitle(title);
        return mapboxMap.addMarker(markerOptions);
    }

    private void setCameraPosition(LatLng location) {
        CameraPosition position = new CameraPosition.Builder()
                .target(location)
                .zoom(10)
                .build();
        mapboxMap.setCameraPosition(position);
    }

    private void showLocationUpdatedToast() {
        Toast.makeText(requireContext(), "Location updated", Toast.LENGTH_SHORT).show();
    }

    private void showBusDistanceToast(float distance) {
        Toast.makeText(requireContext(), "Bus is " + distance + " meters away", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationReceiver != null) {
            requireContext().unregisterReceiver(locationReceiver);
            locationReceiver = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
