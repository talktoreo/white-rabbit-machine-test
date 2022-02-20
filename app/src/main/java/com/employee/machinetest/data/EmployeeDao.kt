package com.employee.machinetest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employee: List<EmployeeResponseItem>)

    @Query("SELECT * FROM employeeResponseItem")
        fun getAlldata() :LiveData<List<EmployeeResponseItem>>

    @Query("SELECT * FROM employeeResponseItem")
    fun getdata() :List<EmployeeResponseItem>

//    @Query("Select * from employeeTable order by id ASC")
//    fun getAllNotes() : LiveData<List<Employee>>
//
//    @Query("Select * from employeeTable WHERE id = :noteId")
//    fun getSelectedNote(noteId:Int) : Employee
//
    @Query("SELECT * FROM employeeresponseitem WHERE email LIKE :seachKey")
    fun getSearchData(seachKey:String) : Flow<List<EmployeeResponseItem>>
}