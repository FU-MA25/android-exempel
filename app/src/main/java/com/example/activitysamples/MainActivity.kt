package com.example.activitysamples

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var employee: Employee? = Employee("Bill", 50)
    private lateinit var nameText: TextView


    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){

            val updatedEmployee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getSerializableExtra("updatedEmployee", Employee::class.java)
            } else {
                result.data?.getSerializableExtra("updatedEmployee") as Employee
            }
            employee = updatedEmployee
            showName()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.tv_name)

        showName()

        val detailsButton = findViewById<Button>(R.id.btn_details)
        val editButton = findViewById<Button>(R.id.btn_edit)

        detailsButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("employee",employee)
            startActivity(intent)


        }

        editButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("employee", employee)
            editLauncher.launch(intent)
        }

    }


    fun showName(){
        nameText.text = employee?.name
    }
}