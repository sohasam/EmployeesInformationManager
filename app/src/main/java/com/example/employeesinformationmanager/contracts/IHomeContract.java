package com.example.employeesinformationmanager.contracts;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.util.List;

public interface IHomeContract {
    interface IHomeView{
        void showAddNewView();
        void showDetailsView(Employee employee);
        void showEditView(Employee employee);
        void showDeleteDialogue(Employee employee);
        void  rendreEmployees(List<Employee> employees);

      }
    interface IHomePresenter{
        void getEmployees();
    }
    interface IHomeModel{
        List<Employee> getEmployees();
    }

}
