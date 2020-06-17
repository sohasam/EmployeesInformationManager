package com.example.employeesinformationmanager.contracts;

import com.example.employeesinformationmanager.data.Entities.Employee;

public interface IHomeContract {
    interface IHomeView{
        void showDetailsPage(Employee employee);
        void showEditPage(Employee employee);
        void showDeleteDialogue(Employee employee);
      }
    interface IHomePresenter{

    }
    interface IHomeModel{

    }

}
