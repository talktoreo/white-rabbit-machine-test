package com.employee.machinetest.api

import com.employee.machinetest.data.EmployeeResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyApi {

    companion object {
        const val BASE_URL = "http://www.mocky.io/v2/"
    }

    @GET("5d565297300000680030a986")
    suspend fun employeeList() : List<EmployeeResponseItem>
}