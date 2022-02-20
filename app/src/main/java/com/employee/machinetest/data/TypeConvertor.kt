package com.employee.machinetest.data

import androidx.room.TypeConverter
import com.google.gson.Gson


class TypeConvertor {
//    @TypeConverter
//    fun fromAddress(address: Address): String {
//        return Gson().toJson(address).toString()
//    }
//
//    @TypeConverter
//    fun toAddress(address: String): Address {
//        return Gson().fromJson(address,Address)
//    }

    @TypeConverter
    fun fromCompany(company: Company?): String? {
        return company?.name.toString()
    }

    @TypeConverter
    fun toCompany(name: String?): Company? {
        return Company("","",name)
    }


}