package com.rdev.bstrack.sheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rdev.bstrack.R;

public class ProfileSheet extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for the profile sheet
        View view = inflater.inflate(R.layout.my_profile_sheet, container, true);

        view.findViewById(R.id.closeProfileButton).setOnClickListener(v -> {
            this.dismiss();
        });

        view.findViewById(R.id.editProfileButton).setOnClickListener(v -> {
            this.dismiss();
            UpdateProfileSheet updateProfileSheet = new UpdateProfileSheet();
            updateProfileSheet.show(getParentFragmentManager(),updateProfileSheet.getTag());
        });

        TextView emailView = view.findViewById(R.id.name);
        TextView contactView = view.findViewById(R.id.contact);
        TextView genderView = view.findViewById(R.id.gender);
        TextView busView = view.findViewById(R.id.bus);


        return view;
    }


}
