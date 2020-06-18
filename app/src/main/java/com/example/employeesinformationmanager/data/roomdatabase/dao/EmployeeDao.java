package com.example.employeesinformationmanager.data.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();
    @Query("SELECT * FROM Employee WHERE employee_name  LIKE '%'||:employeeName||'%'")
    List<Employee> getEmployeesByName(String employeeName);
    @Query("SELECT * FROM Employee WHERE employeeId  LIKE :employeeId ")
    Employee getEmployeesById(int  employeeId);

    @Update
    public void updateUsers(Employee employee);


    @Delete
    void deleteEmployee(Employee employee);
    @Insert
    void insertEmployee(Employee employee);


}
