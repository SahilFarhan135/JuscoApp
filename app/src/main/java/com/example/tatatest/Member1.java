package com.example.tatatest;

public class Member1 {

  private String Name,Purpose ,Email,Phone,Date;

  public Member1(String name, String purpose, String email, String phone,String date) {
    Name = name;
    Purpose = purpose;
    Email = email;
    Phone = phone;
    Date=date;
  }

  public String getDate() {
    return Date;
  }

  public void setDate(String date) {
    Date = date;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getPurpose() {
    return Purpose;
  }

  public void setPurpose(String purpose) {
    Purpose = purpose;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getPhone() {
    return Phone;
  }

  public void setPhone(String phone) {
    Phone = phone;
  }
}
