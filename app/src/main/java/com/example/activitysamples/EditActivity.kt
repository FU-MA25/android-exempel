package com.example.activitysamples

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditActivity : AppCompatActivity() {

    private lateinit var inputName: EditText
    private lateinit var inputSalary: EditText

    private lateinit var employee: Employee


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        inputName = findViewById(R.id.et_name)
        inputSalary = findViewById(R.id.et_salary)
        val saveButton = findViewById<Button>(R.id.btn_save)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            employee = intent.getSerializableExtra("employee", Employee::class.java)!!
        } else {
            employee = intent.getSerializableExtra("employee") as Employee
        }

        inputName.setText(employee.name)
        inputSalary.setText(employee.salary.toString())

        saveButton.setOnClickListener {
            employee.name = inputName.text.toString()
            employee.salary = inputSalary.text.toString().toIntOrNull()

            val resultIntent = Intent().apply {
                putExtra("updatedEmployee", employee)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }







    }
}