package com.example.tatatest;

class empData {
    private String Id,currentDate,EntryTime,present;

    public empData(String id, String currentDate, String EntryTime, String present) {
        Id = id;
        this.currentDate = currentDate;
        this.EntryTime = EntryTime;
        this.present = present;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(String EntryTime) {
        EntryTime = EntryTime;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
