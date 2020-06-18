package com.example.employeesinformationmanager.contracts;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public interface IEmployeeDetailsContract {
    interface IDetailsEmployeePresenter{
        void getEmployeeDetails(int employeeId);

    }
    interface IDetailsEmployeeView{
        void  ShowEmployeeDetails(Employee employee);

    }
    interface IDetailsEmployeeModel{
Employee getEmployeeDetails(int employeeId);
    }

}
