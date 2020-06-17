package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.contracts.IHomeContract;
import com.example.employeesinformationmanager.data.AppDatabase;
import com.example.employeesinformationmanager.data.Entities.Employee;
import com.example.employeesinformationmanager.data.dao.EmployeeDao;
import com.example.employeesinformationmanager.ui.adapter.InformationListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IHomeContract.IHomeView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Employee> personsArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_View_employees_information);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Employee emp1 = new Employee("soha","soha@gmail.coooooooooooooom");
        Employee emp2 = new Employee("samar","soha@gmail.coooooooooooooom");

//Test Room
        AppDatabase appDatabase =AppDatabase.getInstance(this);
       appDatabase.employeeDao().insertEmployee(emp1);
     personsArray = appDatabase.employeeDao().getAllEmployees();
//
//
  recyclerView.setAdapter(new InformationListAdapter(personsArray,this,this) );
Log.i("TAG123", "onClick:  Details...... " +personsArray.get(0).getName());

    }


    @Override
    public void showDetailsPage(Employee employee) {
        Log.i("TAG123", "onClick:  Details...... " +employee.getName());

    }

    @Override
    public void showEditPage(Employee employee) {
        Log.i("TAG123", "onClick:  showEditPage...... " +employee.getName());

    }

    @Override
    public void showDeleteDialogue(Employee employee) {
        Log.i("TAG123", "onClick:  showDeleteDialogue..... " +employee.getName());


    }
}
