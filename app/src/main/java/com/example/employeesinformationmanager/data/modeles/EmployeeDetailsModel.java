package com.example.employeesinformationmanager.data.modeles;

import android.content.Context;
import android.util.Log;

import com.example.employeesinformationmanager.contracts.IEmployeeDetailsContract;
import com.example.employeesinformationmanager.data.roomdatabase.AppDatabase;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public class EmployeeDetailsModel implements IEmployeeDetailsContract.IDetailsEmployeeModel {
    AppDatabase appDatabase;

    public EmployeeDetailsModel(Context context) {
        appDatabase =AppDatabase.getInstance(context);
    }
    @Override
    public Employee getEmployeeDetails(int employeeId) {
        Employee employee= appDatabase.employeeDao().getEmployeesById(employeeId);
        return employee;
    }
}
