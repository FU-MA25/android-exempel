package com.example.productmanager

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private  lateinit var listView: ListView
//    private lateinit var editText: EditText
//    private lateinit var button: Button
    private lateinit var topInputCArd: View
    private lateinit var bottomInputCard: View
    private lateinit var editTextTop: EditText
    private lateinit var editTextBottom: EditText


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
//        editText = findViewById(R.id.et_product)
//        button = findViewById(R.id.btn_add_product)

        topInputCArd = findViewById(R.id.custom_input_top)
        bottomInputCard = findViewById(R.id.custom_input_bottom)

        editTextTop = topInputCArd.findViewById(R.id.et_product)
        editTextBottom = bottomInputCard.findViewById(R.id.et_product)

        val inputTitleTop = topInputCArd.findViewById<TextView>(R.id.tv_input_title)
        val inpuTitleBottom = bottomInputCard.findViewById<TextView>(R.id.tv_input_title)

        inputTitleTop.text = "jag är uppe"
        inpuTitleBottom.text = "jag är nere"



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

        val topButton = topInputCArd.findViewById<Button>(R.id.btn_add_product)
        val bottomButton = bottomInputCard.findViewById<Button>(R.id.btn_add_product)

        topButton.setOnClickListener {
            addItem(editTextTop)
        }
        bottomButton.setOnClickListener {
            addItem(editTextBottom)
        }

//        button.setOnClickListener {
//            addItem()
//        }

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

    private fun addItem(editText: EditText) {
        val newItem = editText.text.toString().trim()

        if (newItem.isEmpty()) {
            Toast.makeText(this, " fält får ej vara tomt", Toast.LENGTH_SHORT).show()
            return
        }

        when (spinner.selectedItemPosition) {
            0 -> fruitList.add(newItem)
            1 -> vegetableList.add(newItem)
            2 -> rootVegList.add(newItem)
            3 -> berryList.add(newItem)

        }

        updateListView(spinner.selectedItemPosition)

        editText.text.clear()
        Toast.makeText(this, "objekt tillagt", Toast.LENGTH_SHORT).show()
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