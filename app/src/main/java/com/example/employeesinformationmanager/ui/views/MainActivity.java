package com.example.employeesinformationmanager.ui.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IHomeContract;
import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.presenters.HomePresenter;
import com.example.employeesinformationmanager.ui.adapter.InformationListAdapter;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IHomeContract.IHomeView {
    private RecyclerView recyclerView;
    private Button btnAddEmployee;
    private SearchView searchView;
    private RecyclerView.LayoutManager layoutManager;
    private IHomeContract.IHomePresenter presenter;
    private List<Employee> employeesArray = new ArrayList<>();
    InformationListAdapter informationListAdapter = new InformationListAdapter(employeesArray, this, this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new HomePresenter(this, this);
        recyclerView = findViewById(R.id.recycler_View_employees_information);
        searchView = findViewById(R.id.search_view);



        btnAddEmployee = findViewById(R.id.btn_add_employee);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(informationListAdapter);
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewView();
            }
        });
        searchView.setQueryHint("Search here for employees");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                presenter.getEmployees();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchWithName(newText.trim());
                return false;
            }


        });

        /////

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getEmployees();

    }

    @Override
    public void showAddNewView() {
        Intent intent = new Intent(MainActivity.this, AddEmployee.class);
        intent.putExtra("isEdit",false);
        startActivity(intent);


    }

    @Override
    public void showDetailsView(Employee employee) {
        Intent intent = new Intent(MainActivity.this, EmployeeDetailsView.class);
        intent.putExtra("Id", employee.getEmployeeId());
        startActivity(intent);
    }

    @Override
    public void showEditView(Employee employee) {
        Intent intent = new Intent(MainActivity.this, AddEmployee.class);
        intent.putExtra("isEdit",true);
        intent.putExtra("Id",employee.getEmployeeId());
        startActivity(intent);
    }

    @Override
    public void showDeleteDialogue(Employee employee) {
        Log.i("TAG123", "onClick:  showDeleteDialogue..... " + employee.getName());


    }

    @Override
    public void rendreEmployees(List<Employee> employees) {

        employeesArray.clear();
        employeesArray.addAll(employees);
        informationListAdapter.notifyDataSetChanged();
    }
    ///////////////////////////////


}
