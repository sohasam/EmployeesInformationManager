package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IHomeContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.HomePresenter;
import com.example.employeesinformationmanager.ui.adapter.InformationListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IHomeContract.IHomeView {
    private RecyclerView recyclerView;
    private Button btnAddEmployee;
    private RecyclerView.LayoutManager layoutManager;
    private IHomeContract.IHomePresenter presenter;
    private List<Employee> employeesArray= new ArrayList<>();
    InformationListAdapter informationListAdapter=new InformationListAdapter(employeesArray,this,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new HomePresenter(this,this);
        recyclerView= findViewById(R.id.recycler_View_employees_information);
        btnAddEmployee= findViewById(R.id.btn_add_employee);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( informationListAdapter);
        presenter.getEmployees();
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showAddNewView();
    }
});
    }


    @Override
    public void showAddNewView() {
        Intent intent=new Intent(MainActivity.this,AddEmployee.class);
        startActivity(intent);


    }

    @Override
    public void showDetailsView(Employee employee) {
        Log.i("TAG123", "onClick:  Details...... " +employee.getName());

    }

    @Override
    public void showEditView(Employee employee) {
        Log.i("TAG123", "onClick:  showEditPage...... " +employee.getName());

    }

    @Override
    public void showDeleteDialogue(Employee employee) {
        Log.i("TAG123", "onClick:  showDeleteDialogue..... " +employee.getName());


    }

    @Override
    public void rendreEmployees(List<Employee> employees) {

        employeesArray.clear();
        employeesArray .addAll(employees);
        informationListAdapter.notifyDataSetChanged();
    }
}
