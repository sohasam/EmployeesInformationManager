package com.example.employeesinformationmanager.presenters;

import android.content.Context;
import android.util.Log;

import com.example.employeesinformationmanager.contracts.IEmployeeDetailsContract;
import com.example.employeesinformationmanager.data.modeles.EmployeeDetailsModel;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public class EmployeeDetailsPresnter implements IEmployeeDetailsContract.IDetailsEmployeePresenter {

    IEmployeeDetailsContract.IDetailsEmployeeView view;
    IEmployeeDetailsContract.IDetailsEmployeeModel model;
    Context context;

    public EmployeeDetailsPresnter(IEmployeeDetailsContract.IDetailsEmployeeView view, Context context) {
        this.view = view;
        this.context = context;
        model =new EmployeeDetailsModel(context);
                   }

    @Override
    public void getEmployeeDetails(int employeeId) {
        Employee employee =model.getEmployeeDetails(employeeId);

        view.ShowEmployeeDetails(employee );
    }
}
