package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IAddEmployeeContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.AddEmployeePresenter;

public class AddEmployee extends AppCompatActivity  implements IAddEmployeeContract.IAddEmployeeView {
    IAddEmployeeContract.IAddEmployeePresenter presenter;
    Button btnAdd;
    EditText fullNameEditText;
    EditText emailEditText;
    Button changePicBtn;


    Employee employee = new  Employee("","");
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        presenter =new  AddEmployeePresenter(this,this);
        btnAdd=findViewById(R.id.btn_Add);
        changePicBtn= findViewById(R.id.btn_change_pic);
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
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getApplicationContext(),"Faild To Add Employee 'Full Name Is required'", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSuccessToast() {
        Toast.makeText(getApplicationContext(),"Employee Added Successfully", Toast.LENGTH_SHORT).show();
        AddEmployee.this.finish();

    }
}
