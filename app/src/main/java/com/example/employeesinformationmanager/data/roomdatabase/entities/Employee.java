package com.example.employeesinformationmanager.data.roomdatabase.entities;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

        @PrimaryKey(autoGenerate = true)
          public int employeeId;

    // Dom't Forget Add List Of Skills
    @ColumnInfo(name = "employee_name")
    private String name;
    @ColumnInfo(name = "employee_email")
    private String email;
    @ColumnInfo(name = "Img_Uri")
    private String  imgUri;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
