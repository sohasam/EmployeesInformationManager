package com.example.employeesinformationmanager.contracts;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public interface IAddEmployeeContract {
  public interface IAddEmployeeView{
      void  showErrorToast();
      void showSuccessToast();
    }
    public  interface IAddEmployeePresenter{
    void validateData(Employee employee);
    void  AddEmployee(Employee employee);
    }
    public   interface IAddEmployeeModel{
      void  AddEmployee(Employee employee);

    }
}
