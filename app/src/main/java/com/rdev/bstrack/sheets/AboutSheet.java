package com.rdev.bstrack.sheets;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rdev.bstrack.R;

public class AboutSheet extends BottomSheetDialogFragment {

    private Button closeButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for the profile sheet
        View view = inflater.inflate(R.layout.about_us_sheet, container, true);

        closeButton = view.findViewById(R.id.closeAboutButton);


        closeButton.setOnClickListener(v -> {
            this.dismiss();
        });

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View layout = view.findViewById(R.id.about_sheet);
        if (layout != null) {
            layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        }

        // Get the BottomSheetBehavior associated with the parent view
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());

        // Set the BottomSheet to the expanded state
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        // Assuming 'layout' is already initialized somewhere in your code
        if (layout != null) {
            // Set the minimum height of the layout to the screen height
            layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels-200);
        } else {
            throw new IllegalStateException("Layout cannot be null");
        }
        bottomSheetBehavior.setDraggable(false);


    }


}
