package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IEmployeeDetailsContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.EmployeeDetailsPresnter;

import java.io.FileDescriptor;
import java.io.IOException;

public class EmployeeDetailsView extends AppCompatActivity implements IEmployeeDetailsContract.IDetailsEmployeeView {
    IEmployeeDetailsContract.IDetailsEmployeePresenter presenter;
    TextView nameTextView;
    TextView emailTextView;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        nameTextView=findViewById(R.id.full_name_text_view);
        emailTextView=findViewById(R.id.email_text_view);
        imgView=findViewById(R.id.img_view);
        presenter = new EmployeeDetailsPresnter(this, this);
        Intent intent = getIntent();
        int employeeId = intent.getIntExtra("Id", 1);
        Log.i("EMPLOYEEID",employeeId+"");
        presenter.getEmployeeDetails(employeeId);
    }

    @Override
    public void ShowEmployeeDetails(Employee employee) {
        nameTextView.setText(employee.getName());
        emailTextView.setText(employee.getEmail());
        if (employee.getImgUri() != null) {
            try {
                Bitmap bitmap = getBitmapFromUri(Uri.parse((employee.getImgUri())));
                imgView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}
