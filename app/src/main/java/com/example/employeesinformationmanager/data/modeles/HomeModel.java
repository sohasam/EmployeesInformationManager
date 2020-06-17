package com.example.employeesinformationmanager.data.modeles;

import android.content.Context;

import com.example.employeesinformationmanager.contracts.IHomeContract;
import com.example.employeesinformationmanager.data.roomdatabase.AppDatabase;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.util.List;

public class HomeModel implements IHomeContract.IHomeModel {

    AppDatabase appDatabase;

    public HomeModel(Context context) {
        appDatabase =AppDatabase.getInstance(context);
    }

    @Override
    public List<Employee> getEmployees() {
        return appDatabase.employeeDao().getAllEmployees();
    }
}
