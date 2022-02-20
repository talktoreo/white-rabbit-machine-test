package com.employee.machinetest.data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.employee.machinetest.api.MyApi
import com.employee.machinetest.di.ApiExceptions
import com.employee.machinetest.di.Coroutines
import com.employee.machinetest.di.NoInternetException
import com.employee.machinetest.di.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val api: MyApi,
    private val db: EmployeeDatabase
) : SafeApiRequest() {
    val employee = MutableLiveData<List<EmployeeResponseItem>>()
    var enwemployee = MutableLiveData<List<EmployeeResponseItem>>()
    var searchemployee = MutableLiveData<List<EmployeeResponseItem>>()
//    init {
//        employee.observeForever {
//            saveEmployee(it)
//        }
//    }

        suspend fun getEmployees():LiveData<List<EmployeeResponseItem>>{
        return withContext(Dispatchers.IO){
            fetchEmployees()
            db.getEmployeeDao().getAlldata()
        }
    }
    fun searchByEmail(query: String): MutableLiveData<List<EmployeeResponseItem>> {
//        Coroutines.io {
//            fetchEmployees()
//        }
//        searchemployee.postValue(db.getEmployeeDao().getSearchData(query))
        return searchemployee
    }

    fun searchByEmailNew(query: String): Flow<List<EmployeeResponseItem>> {
        return db.getEmployeeDao().getSearchData(query)
    }

    suspend fun fetchEmployees(): LiveData<List<EmployeeResponseItem>> {
        try {
            val response = api.employeeList()
            saveEmployee(response)
            employee.postValue(response)
            Log.e("sizeeeeee", "data${response.size}")

            return employee
        } catch (e: ApiExceptions) {
            Log.e("Exception ", "::" + e.message);

        } catch (e: NoInternetException) {
            Log.e("Exception ", "::" + e.message);
        }

        return employee
    }

    fun saveEmployee(employees: List<EmployeeResponseItem>) {
        Coroutines.io {
            db.getEmployeeDao().insert(employees)
            Log.e("anotherrrr",employees.size.toString())
            Log.e("anotherrrr1",db.getEmployeeDao().getAlldata().value?.size.toString())
        }
    }

    fun getAllEmployee(): MutableLiveData<List<EmployeeResponseItem>> {
        enwemployee.postValue(db.getEmployeeDao().getdata())
        return enwemployee
    }
}