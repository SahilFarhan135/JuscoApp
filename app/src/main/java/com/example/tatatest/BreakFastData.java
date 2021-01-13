package com.example.tatatest;

public class BreakFastData {
    String Name,Id,Slot,Item,Time;

    public BreakFastData(String name, String id, String slot, String item, String time) {
        Name = name;
        Id = id;
        Slot = slot;
        Item = item;
        Time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
