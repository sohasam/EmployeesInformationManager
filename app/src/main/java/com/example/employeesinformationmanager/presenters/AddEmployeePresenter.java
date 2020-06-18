package com.example.employeesinformationmanager.presenters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.employeesinformationmanager.contracts.IAddEmployeeContract;
import com.example.employeesinformationmanager.data.modeles.AddEmployeeModel;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEmployeePresenter implements IAddEmployeeContract.IAddEmployeePresenter {
    IAddEmployeeContract.IAddEmployeeModel model;
    IAddEmployeeContract.IAddEmployeeView view;
    Context context;

    public AddEmployeePresenter(IAddEmployeeContract.IAddEmployeeView view, Context context) {
        this.context = context;
        this.view = view;
        model = new AddEmployeeModel(context);
    }

    @Override
    public void validateData(Employee employee) {
        if (employee.getName().trim().equals("")) {
            view.showErrorToast();
        } else {
            AddEmployee(employee);
        }

    }

    @Override
    public void AddEmployee(Employee employee) {
        model.AddEmployee(employee);
        view.showSuccessToast();
    }

    @Override
    public void takePhoto() {
        dispatchTakePictureIntent();

    }

    @Override
    public void onSuccessToTakePic() {
        view.setPic(currentPhotoPath);
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + "timeStamp" + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {

                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context.getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                view.showPictureTakingActivity(takePictureIntent);

            }

        }

    }
}
