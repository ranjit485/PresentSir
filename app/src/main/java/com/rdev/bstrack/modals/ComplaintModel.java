package com.rdev.bstrack.modals;

public class ComplaintModel {

    int complaintId;
    Boolean complaintStatus;
    String complaintNo;
    String complaintType;
    String complaintDesc;

    public ComplaintModel(int complaintId, Boolean complaintStatus, String complaintNo, String complaintType, String complaintDesc) {
        this.complaintId = complaintId;
        this.complaintStatus = complaintStatus;
        this.complaintNo = complaintNo;
        this.complaintType = complaintType;
        this.complaintDesc = complaintDesc;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public Boolean getComplaintStatus() {
        return complaintStatus;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }
}
