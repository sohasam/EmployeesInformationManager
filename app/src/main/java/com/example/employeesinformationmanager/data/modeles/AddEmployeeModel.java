package com.example.employeesinformationmanager.data.modeles;

import android.content.Context;

import com.example.employeesinformationmanager.contracts.IAddEmployeeContract;
import com.example.employeesinformationmanager.data.roomdatabase.AppDatabase;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public class AddEmployeeModel implements IAddEmployeeContract.IAddEmployeeModel {
    AppDatabase appDatabase;

    public AddEmployeeModel(Context context) {
        appDatabase =AppDatabase.getInstance(context);
    }

    @Override
    public void AddEmployee(Employee employee) {
        appDatabase.employeeDao().insertEmployee(employee);
    }
}
