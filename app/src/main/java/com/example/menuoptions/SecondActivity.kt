package com.example.menuoptions

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity(), CustomDialogFragment.CustomDialogListener {
    private val viewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val textView = findViewById<TextView>(R.id.textView)
        val receivedText = intent.getStringExtra("data_key")
        textView.text = receivedText

        val price = intent.getDoubleExtra("price", 0.0)
        val editText1 = findViewById<EditText>(R.id.ed2)
        editText1.setText(price.toString())

        val ratingTV: TextView = findViewById(R.id.textView2)
        ratingTV.setOnClickListener {
            val dialogVar = CustomDialogFragment()
            dialogVar.show(supportFragmentManager, "Custom Dialog")
        }

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            calculateTotal(price)
        }

        // Restore the state
        findViewById<TextView>(R.id.textView2).text = "Percentage: ${viewModel.percentage}%"
        findViewById<TextView>(R.id.textView).text = "You need to pay: ${viewModel.total}"
    }

    override fun onDialogPositiveClick(percentage: Int) {
        viewModel.percentage = percentage
        findViewById<TextView>(R.id.textView2).text = "Percentage: $percentage%"
    }

    private fun calculateTotal(price: Double) {
        val editText2 = findViewById<EditText>(R.id.ed1)
        val num2 = editText2.text.toString().toDoubleOrNull() ?: 0.0
        viewModel.total = price * num2 * (viewModel.percentage / 100.0)
        findViewById<TextView>(R.id.textView).text = "You need to pay: ${viewModel.total}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.my_first_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item2 -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem1 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Sub-item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
