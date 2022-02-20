package com.employee.machinetest.di

import android.content.Context
import com.employee.machinetest.api.MyApi
import com.employee.machinetest.data.EmployeeDao
import com.employee.machinetest.data.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(MyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit) : MyApi =
        retrofit.create(MyApi::class.java)

    @Provides
    @Singleton
    fun provideEmployeeDataBase(@ApplicationContext context: Context): EmployeeDatabase {
        return EmployeeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideEmployeeDao(employeeDatabase: EmployeeDatabase): EmployeeDao {
        return employeeDatabase.getEmployeeDao()
    }
}