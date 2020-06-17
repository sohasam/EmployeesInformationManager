package com.example.employeesinformationmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.employeesinformationmanager.data.Entities.Employee;
import com.example.employeesinformationmanager.data.dao.EmployeeDao;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase{
    private static AppDatabase INSTANCE;
    public abstract  EmployeeDao employeeDao();
    public   static  AppDatabase  getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =  Room.databaseBuilder(context, AppDatabase.class, "EmployeesInformation")
                    .allowMainThreadQueries()
                   .fallbackToDestructiveMigration()
                    .build();
        }
        return  INSTANCE;
    }


}
