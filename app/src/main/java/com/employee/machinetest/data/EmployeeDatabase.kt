package com.employee.machinetest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [EmployeeResponseItem::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(TypeConvertor::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun getEmployeeDao():EmployeeDao

    companion object {
        fun getInstance(context: Context): EmployeeDatabase {
            return Room.databaseBuilder(context, EmployeeDatabase::class.java, "employee_database_new").allowMainThreadQueries().build()
        }
    }

}