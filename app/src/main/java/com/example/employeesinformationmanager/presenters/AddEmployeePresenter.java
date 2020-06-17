package com.example.employeesinformationmanager.presenters;

import android.content.Context;

import com.example.employeesinformationmanager.contracts.IAddEmployeeContract;
import com.example.employeesinformationmanager.data.modeles.AddEmployeeModel;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public class AddEmployeePresenter implements IAddEmployeeContract.IAddEmployeePresenter {
    IAddEmployeeContract.IAddEmployeeModel model;
    IAddEmployeeContract.IAddEmployeeView view;
Context context;
    public AddEmployeePresenter(IAddEmployeeContract.IAddEmployeeView view,Context context) {
        this.context=context;
        this.view = view;
        model = new AddEmployeeModel(context);
    }

    @Override
    public void validateData(Employee employee) {
        if(employee.getName().trim().equals(""))
        {
            view.showErrorToast();
        }
        else {
            AddEmployee(employee);
        }

    }

    @Override
    public void AddEmployee(Employee employee) {
        model.AddEmployee(employee);
        view.showSuccessToast();
      }
}
