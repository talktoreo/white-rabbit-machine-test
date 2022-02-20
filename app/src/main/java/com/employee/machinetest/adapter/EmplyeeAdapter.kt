package com.employee.machinetest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.employee.machinetest.R
import com.employee.machinetest.data.EmployeeResponseItem
import com.employee.machinetest.databinding.EmployeeItemBinding

class EmployeeAdapter(
    val mEmployeeAdapterListner: EmployeeAdapterListner
) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {
    private val employeelist = ArrayList<EmployeeResponseItem>()

    inner class MyViewHolder(val databinding: EmployeeItemBinding) :
        RecyclerView.ViewHolder(databinding.root) {
        fun bindUi(mEmployeeResponseItem: EmployeeResponseItem) {
            databinding.apply {
                Glide.with(itemView)
                    .load(mEmployeeResponseItem.profileImage)
                    .error(R.drawable.ic_launcher_background)
                    .into(imProfile)

                tvCompanyName.text = mEmployeeResponseItem.company?.name
                tvEmployeeName.text = mEmployeeResponseItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val databinding = EmployeeItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )

        return MyViewHolder(databinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindUi(employeelist[position])

        holder.itemView.setOnClickListener(View.OnClickListener {
            mEmployeeAdapterListner.onEmployeeSelect(employeelist[position])
        })
    }

    override fun getItemCount(): Int {
        return employeelist.size
    }

    fun updateList(newList: List<EmployeeResponseItem>) {
        employeelist.clear()
        employeelist.addAll(newList)
        notifyDataSetChanged()
    }
}

interface EmployeeAdapterListner {
    fun onEmployeeSelect(employeeData: EmployeeResponseItem)
}