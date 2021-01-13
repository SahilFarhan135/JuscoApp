package com.example.tatatest;

public class ShowData {
    String name,Purpose,Date;
    private String status;

    public ShowData(String name, String purpose, String date) {
        this.name = name;
        Purpose = purpose;
        Date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    public String toString(){
        return this.name;
    }
}
