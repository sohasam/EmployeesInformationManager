package com.example.employeesinformationmanager.contracts;

import android.content.Intent;
import android.net.Uri;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.util.List;

public interface IAddEmployeeContract {
  public interface IAddEmployeeView{
      void  showErrorToast();
      void showSuccessToast();
      void  showPictureTakingActivity(Intent takePictureIntent);
    void  showGalleryActivity(Intent galleryIntent);
//    void setPic(String currentPhotoPath);
void setPic(Uri uri);

    }
    public  interface IAddEmployeePresenter{
    void validateData(Employee employee);
    void  AddEmployee(Employee employee);

    void  takePhoto();
    void onSuccessToTakePhoto();

    void picImgFomGallery();
      void onSuccessToPicImgFomGallery(Intent data);


    }

    public   interface IAddEmployeeModel{
      void  AddEmployee(Employee employee);
    }
}
