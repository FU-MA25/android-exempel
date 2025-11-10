package com.example.productmanager

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class CustomCardInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context,attrs,defStyleAttr){

    private val title: TextView
    private val input: EditText
    private val button: Button


    init {
        LayoutInflater.from(context).inflate(R.layout.custom_input_card, this, true)

        title = findViewById(R.id.tv_input_title)
        input = findViewById(R.id.et_product)
        button = findViewById(R.id.btn_add_product)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomCardInput)
            title.text = typedArray.getString(R.styleable.CustomCardInput_titleText)
            input.hint = typedArray.getString(R.styleable.CustomCardInput_hintText)
            button.text = typedArray.getString(R.styleable.CustomCardInput_buttonText)
            typedArray.recycle()
        }




    }
    fun getInputText(): String = input.text.toString()

    fun setOnButtonClick(listener: (String) -> Unit) {
        button.setOnClickListener { listener(input.text.toString()) }
    }

}