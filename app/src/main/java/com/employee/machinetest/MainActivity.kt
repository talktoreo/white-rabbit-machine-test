package com.employee.machinetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.employee.machinetest.data.EmployeeViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.employee.machinetest.adapter.EmployeeAdapter
import com.employee.machinetest.adapter.EmployeeAdapterListner
import com.employee.machinetest.data.EmployeeResponseItem
import com.maharani.maharaniapp.Utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EmployeeAdapterListner {

    private lateinit var mEmployeeAdapter: EmployeeAdapter
    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mEmployeeAdapter = EmployeeAdapter(this)
        employeeRecycler.adapter = mEmployeeAdapter

        lifecycleScope.launch() {
//            if (NetworkUtils.isNetworkConnected(this@MainActivity)) {
            viewModel.getData().observe(this@MainActivity, Observer {
//                mEmployeeAdapter.updateList(it)
            })
//                viewModel.searchData.observe(this@MainActivity, Observer {
//                    Log.e("responseee", it.size.toString())
//                    mEmployeeAdapter.updateList(it)
//                })
//            } else {
//                Toast.makeText(this@MainActivity,"Network not available",Toast.LENGTH_LONG).show()
//            }
        }

        viewModel.allemployees.observe(this, Observer {
            mEmployeeAdapter.updateList(it)
            Log.e("dataaaaaaaa", it.size.toString())
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                searchData(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchData(p0)
                return true
            }

        })

    }

    override fun onEmployeeSelect(employeeData: EmployeeResponseItem) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("data",employeeData)
        startActivity(intent)
    }

    fun searchData(quert : String?){
        val searchQuery = "%$quert%"
        viewModel.search(searchQuery).observe(this,{
            Log.e("yoooooooo", it.size.toString())
            mEmployeeAdapter.updateList(it)
        })
    }
}