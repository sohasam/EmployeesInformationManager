package com.example.employeesinformationmanager.contracts;

import android.content.Intent;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

public interface IAddEmployeeContract {
  public interface IAddEmployeeView{
      void  showErrorToast();
      void showSuccessToast();
      void  showPictureTakingActivity(Intent takePictureIntent);
    void setPic(String currentPhotoPath);

    }
    public  interface IAddEmployeePresenter{
    void validateData(Employee employee);
    void  AddEmployee(Employee employee);
    void  takePhoto();
    void onSuccessToTakePic();

    }

    public   interface IAddEmployeeModel{
      void  AddEmployee(Employee employee);

    }
}
