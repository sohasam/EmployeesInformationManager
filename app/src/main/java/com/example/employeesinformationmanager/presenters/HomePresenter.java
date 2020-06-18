package com.example.employeesinformationmanager.presenters;

import android.content.Context;

import com.example.employeesinformationmanager.contracts.IHomeContract;
import com.example.employeesinformationmanager.data.modeles.HomeModel;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.util.List;

public class HomePresenter implements IHomeContract.IHomePresenter {
   private IHomeContract.IHomeView view;
   private Context context;
   private IHomeContract.IHomeModel model;


    public HomePresenter(IHomeContract.IHomeView view, Context context) {
        this.view = view;
        this.context = context;
        model = new HomeModel(context);
    }


    @Override
    public void getEmployees() {
        List<Employee> employees= model.getEmployees();
        view.rendreEmployees(employees);

    }

    @Override
    public void searchWithName(String name) {
        List<Employee> employees= model.searchWithName(name);
        view.rendreEmployees(employees);
    }
}
