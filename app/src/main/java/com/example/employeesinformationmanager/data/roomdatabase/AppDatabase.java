package com.example.employeesinformationmanager.data.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.employeesinformationmanager.data.roomdatabase.entities.Employee;
import com.example.employeesinformationmanager.data.roomdatabase.dao.*;

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
