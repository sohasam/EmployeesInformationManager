package com.example.employeesinformationmanager.data.roomdatabase.entities;

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
