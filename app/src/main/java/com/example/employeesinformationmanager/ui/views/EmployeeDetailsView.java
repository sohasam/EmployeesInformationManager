package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IEmployeeDetailsContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.EmployeeDetailsPresnter;

public class EmployeeDetailsView extends AppCompatActivity implements IEmployeeDetailsContract.IDetailsEmployeeView {
    IEmployeeDetailsContract.IDetailsEmployeePresenter presenter;
    TextView nameTextView;
    TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        nameTextView=findViewById(R.id.full_name_text_view);
        emailTextView=findViewById(R.id.email_text_view);
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
    }
}
