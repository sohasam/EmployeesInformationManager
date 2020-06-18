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
import android.graphics.Matrix;
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
    Button btnGallery;
    EditText fullNameEditText;
    EditText emailEditText;
    Button changePicBtn;
    ImageView imageView;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_PICK_IMAGE =2;

    Employee employee = new Employee("", "");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        presenter = new AddEmployeePresenter(this, this);
        btnAdd = findViewById(R.id.btn_Add);
        btnGallery=findViewById(R.id.btn_gallery);
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
                presenter.takePhoto();
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.picImgFomGallery();

            }
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
    public void showGalleryActivity(Intent galleryIntent) {
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), REQUEST_PICK_IMAGE);


    }

    @Override
    public void setPic(Uri uri) {
        try {
            employee.setImgUri(uri.toString());
           Bitmap    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
      imageView.setImageURI(uri);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            presenter.onSuccessToTakePhoto();
        }
      else   if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK&& null != data) {
            presenter.onSuccessToPicImgFomGallery(data);
        }
    }



}
