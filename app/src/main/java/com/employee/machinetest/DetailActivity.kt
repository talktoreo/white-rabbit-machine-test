package com.employee.machinetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.employee.machinetest.data.EmployeeResponseItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailModel: EmployeeResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailModel = intent?.getSerializableExtra("data") as EmployeeResponseItem

        Glide.with(this)
            .load(detailModel?.profileImage)
            .error(R.drawable.ic_launcher_background)
            .into(imProfile)

        tvCompany.text = detailModel?.company?.name
        tvName.text = detailModel?.name
        tvEmail.text = detailModel?.email
        tvPhone.text = detailModel?.phone
        tvUsername.text = detailModel?.username
        tvWebsite.text = detailModel?.website

    }
}