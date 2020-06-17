package com.example.employeesinformationmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.employeesinformationmanager.data.Entities.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();
//    @Query("SELECT * FROM notes WHERE note_id LIKE :noteId ")
//    List<NoteModel> getByNoteIdId(int noteId);
    @Delete
    void deleteEmployee(Employee employee);
    @Insert
    void insertEmployee(Employee employee);


}
