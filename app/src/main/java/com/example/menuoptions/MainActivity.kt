package com.example.menuoptions

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val editText = findViewById<EditText>(R.id.num1)
        val button = findViewById<Button>(R.id.button)
        val spinnerVal = findViewById<Spinner>(R.id.spinner1)

        val options = arrayOf("DayTrade", "Long", "Short", "Options")
        var flag: String = "DayTrade"
        spinnerVal.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                flag = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        button.setOnClickListener {
            val inputText = editText.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data_key", "Selected: $flag, Position: $inputText")
            intent.putExtra("price", inputText.toDoubleOrNull() ?: 0.0)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.my_first_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item2 -> {
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem1 -> {
                Toast.makeText(this, "Sub-item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
