package com.rdev.bstrack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.rdev.bstrack.R;
import com.rdev.bstrack.interfaces.ItemListener;
import com.rdev.bstrack.modals.ComplaintModel;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    List<ComplaintModel> complaintModelList;
    private ItemListener listener;

    public ComplaintAdapter(List<ComplaintModel> complaintModelList, ItemListener listener) {
        this.complaintModelList = complaintModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_card, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ComplaintModel complaintModel =complaintModelList.get(holder.getAdapterPosition());

        holder.complaintNo.setText(complaintModel.getComplaintNo());
        holder.complaintDesc.setText(complaintModel.getComplaintDesc());
        holder.complaintType.setText(complaintModel.getComplaintType());

        holder.complaintCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(complaintModelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaintModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView complaintNo;
        TextView complaintType;
        TextView complaintDesc;

        MaterialCardView complaintCard;
        public ViewHolder(View itemView) {
            super(itemView);

            complaintCard =itemView.findViewById(R.id.complaint_BOX);
            complaintNo = itemView.findViewById(R.id.complaint_no);
            complaintType = itemView.findViewById(R.id.complaint_type);
            complaintDesc = itemView.findViewById(R.id.complaint_description);

        }
    }
}
