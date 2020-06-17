package com.example.employeesinformationmanager.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.employeesinformationmanager.R;
import com.example.employeesinformationmanager.ui.adapter.InformationListAdapter;
import com.example.employeesinformationmanager.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<EmployeeModel> personsArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_View_employees_information);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EmployeeModel emp1 = new EmployeeModel("soha","soha@gmail.coooooooooooooom");
        EmployeeModel emp2 = new EmployeeModel("samar","soha@gmail.coooooooooooooom");

        personsArray.add(emp1);
        personsArray.add(emp2);
        personsArray.add(emp1);
        personsArray.add(emp2);
        personsArray.add(emp1);
        personsArray.add(emp2);
        personsArray.add(emp1);
        personsArray.add(emp2);
        personsArray.add(emp1);
        personsArray.add(emp1);
        recyclerView.setAdapter(new InformationListAdapter(personsArray,this) );
    }
}
