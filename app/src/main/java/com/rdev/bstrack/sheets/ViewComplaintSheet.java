package com.rdev.bstrack.sheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rdev.bstrack.R;

public class ViewComplaintSheet extends BottomSheetDialogFragment {

    String complaintType;
    String complaintDesc;

    public ViewComplaintSheet(String complaintType, String complaintDesc) {
        this.complaintType = complaintType;
        this.complaintDesc = complaintDesc;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_complaint_sheet, container, false);

        TextView complaintType = view.findViewById(R.id.view_complaint_type);
        TextView complaintDesc = view.findViewById(R.id.view_complaint_description);

        complaintDesc.setText(this.complaintDesc);
        complaintType.setText(this.complaintType);

        return view;
    }
}
