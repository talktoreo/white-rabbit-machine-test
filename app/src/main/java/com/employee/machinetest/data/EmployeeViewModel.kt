package com.employee.machinetest.data

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.employee.machinetest.di.Coroutines

class EmployeeViewModel @ViewModelInject constructor(
    private val mEmployeeRepository: EmployeeRepository

) : ViewModel() {
     var currentQuery = MutableLiveData("")
    val allemployees: MutableLiveData<List<EmployeeResponseItem>> get() = mEmployeeRepository.getAllEmployee()

//    private val searchFlow = currentQuery.switchMap {
//        mEmployeeRepository.searchByEmail(it)
//    }


//    val searchData : LiveData<List<EmployeeResponseItem>> = searchFlow

    fun search(query : String): LiveData<List<EmployeeResponseItem>> {
        return mEmployeeRepository.searchByEmailNew(query).asLiveData()
    }

    suspend fun getData(): LiveData<List<EmployeeResponseItem>> {
        val employee = mEmployeeRepository.getEmployees()
        return employee
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}