package com.rdev.bstrack.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdev.bstrack.R;
import com.rdev.bstrack.adapter.ComplaintAdapter;
import com.rdev.bstrack.interfaces.ItemListener;
import com.rdev.bstrack.modals.ComplaintModel;
import com.rdev.bstrack.sheets.ComplaintBottomSheet;
import com.rdev.bstrack.sheets.ViewComplaintSheet;

import java.util.ArrayList;
import java.util.List;

public class Complaint extends Fragment implements ItemListener {

    private ComplaintModel complaintModel;
    private ComplaintAdapter complaintAdapter;
    private List<ComplaintModel> complaintModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        FloatingActionButton newComplaintButton = view.findViewById(R.id.new_complaint_button);

        RecyclerView recyclerView = view.findViewById(R.id.complaint_recycle_view);

        complaintModelList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complaintAdapter = new ComplaintAdapter(complaintModelList, this);
        recyclerView.setAdapter(complaintAdapter);

        newComplaintButton.setOnClickListener(v -> {
            ComplaintBottomSheet complaintBottomSheet = new ComplaintBottomSheet();
            complaintBottomSheet.show(getChildFragmentManager(), "complaintBottomSheet");
        });

        for (int i = 0; i < 5; i++) {

            String no = String.valueOf(i);
            complaintModelList.add(new ComplaintModel(i, true, no, "Canteen", "Hello this compliant desc for testing.Hello this compliant desc for testing.Hello this compliant desc for testing."));

        }
        complaintAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onItemClick(ComplaintModel complaintModel) {
        Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
        ViewComplaintSheet viewComplaintSheet = new ViewComplaintSheet(complaintModel.getComplaintType(),complaintModel.getComplaintDesc());
        viewComplaintSheet.show(getChildFragmentManager(), "viewComplaintSheet");
    }
}