package com.example.activitysamples

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {

    lateinit var nameView: TextView
    lateinit var salaryView: TextView

    private lateinit var employee: Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        nameView = findViewById(R.id.tv_name)
        salaryView = findViewById(R.id.tv_salary)
        val backBtn = findViewById<Button>(R.id.btn_back)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            employee = intent.getSerializableExtra("employee", Employee::class.java)!!
        } else {
            employee = intent.getSerializableExtra("employee") as Employee
        }

        nameView.text = "bosse"
        salaryView.text = employee.salary.toString()

        backBtn.setOnClickListener {
            finish()
        }


    }
}