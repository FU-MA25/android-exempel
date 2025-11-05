package com.example.productmanager

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private  lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var button: Button

    private lateinit var listViewAdapter: ArrayAdapter<String>

    private val fruitList = mutableListOf("Äpple","Banan","Apelsin","Päron")
    private val vegetableList = mutableListOf("Gurka","Tomat","Paprika","Broccoli")
    private val rootVegList = mutableListOf("Potatis","Morot","Kålrot","Palsternacka")
    private val berryList = mutableListOf("Jorgubbe","hallon","Vinbbär", "Lingon")

    private val itemList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        spinner = findViewById(R.id.spinner_category)
        listView = findViewById(R.id.lv_products)
        editText = findViewById(R.id.et_product)
        button = findViewById(R.id.btn_add_product)



        val categories = arrayOf("Frukt","Grönsaker","Rotfrukter","Bär")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = spinnerAdapter

        listViewAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, itemList)

        listView.adapter = listViewAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateListView(position)
            }



            override fun onNothingSelected(p0: AdapterView<*>?) {
               //
            }

        }

        // =========== add button ============

        button.setOnClickListener {
            val newItem = editText.text.toString().trim()

            if (newItem.isEmpty()){
                Toast.makeText(this," fält får ej vara tomt", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when(spinner.selectedItemPosition){
                0 -> fruitList.add(newItem)
                1 -> vegetableList.add(newItem)
                2 -> rootVegList.add(newItem)
                3 -> berryList.add(newItem)

            }

            updateListView(spinner.selectedItemPosition)

            editText.text.clear()
            Toast.makeText(this,"objekt tillagt", Toast.LENGTH_SHORT).show()
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener{
            parent, view, position, id ->

            val itemToDelete = itemList[position]

            when (spinner.selectedItemPosition){
                0 -> fruitList.remove(itemToDelete)
                1 -> vegetableList.remove(itemToDelete)
                2 -> rootVegList.remove(itemToDelete)
                3 -> berryList.remove(itemToDelete)
            }
            updateListView(spinner.selectedItemPosition)
            Toast.makeText(this,"${itemToDelete} raderat", Toast.LENGTH_SHORT).show()
            true
        }


    }


    private fun updateListView(position: Int) {

        itemList.clear()
        when(position){
            0 -> itemList.addAll(fruitList)
            1 -> itemList.addAll(vegetableList)
            2 -> itemList.addAll(rootVegList)
            3 -> itemList.addAll(berryList)
        }

        listViewAdapter.notifyDataSetChanged()
    }
}