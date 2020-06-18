package com.example.employeesinformationmanager.ui.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IAddEmployeeContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.AddEmployeePresenter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddEmployee extends AppCompatActivity implements IAddEmployeeContract.IAddEmployeeView {
    IAddEmployeeContract.IAddEmployeePresenter presenter;
    Button btnAdd;
    EditText fullNameEditText;
    EditText emailEditText;
    Button changePicBtn;
    ImageView imageView;
    //static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Employee employee = new Employee("", "");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        presenter = new AddEmployeePresenter(this, this);
        btnAdd = findViewById(R.id.btn_Add);
        changePicBtn = findViewById(R.id.btn_change_pic);
        imageView = findViewById(R.id.img_view);
        fullNameEditText = findViewById(R.id.edit_text_full_name);
        emailEditText = findViewById(R.id.edit_text_full_name);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employee.setName(fullNameEditText.getText().toString());
                employee.setEmail(emailEditText.getText().toString());
                presenter.validateData(employee);
            }
        });
        changePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // takePicture();
                presenter.takePhoto();
            }
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }
//            }
        });
    }


    @Override
    public void showErrorToast() {
        Toast.makeText(getApplicationContext(), "Faild To Add Employee 'Full Name Is required'", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void showSuccessToast() {
        Toast.makeText(getApplicationContext(), "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        AddEmployee.this.finish();

    }

    @Override
    public void showPictureTakingActivity(Intent takePictureIntent) {
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            presenter.onSuccessToTakePic();
        }
    }
    /// save photo


    @Override
    public void setPic(String currentPhotoPath) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        int scaleFactor = 12;
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }


}
