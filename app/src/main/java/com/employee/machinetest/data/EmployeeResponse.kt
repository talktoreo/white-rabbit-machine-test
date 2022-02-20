package com.employee.machinetest.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EmployeeResponse(

	@field:SerializedName("EmployeeResponse")
	val employeeResponse: List<EmployeeResponseItem?>? = null
): Serializable

data class Address(

	@field:SerializedName("zipcode")
	val zipcode: String? = null,

	@field:SerializedName("suite")
	val suite: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null
): Serializable

data class Company(

	@field:SerializedName("bs")
	val bs: String? = null,

	@field:SerializedName("catchPhrase")
	val catchPhrase: String? = null,

	@field:SerializedName("name")
	val name: String? = null
): Serializable

@Entity()
data class EmployeeResponseItem(

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

//	@field:SerializedName("address")
//	val address: Address,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@NonNull
	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
): Serializable
