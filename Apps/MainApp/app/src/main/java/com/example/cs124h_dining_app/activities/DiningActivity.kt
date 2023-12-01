package com.example.cs124h_dining_app.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cs124h_dining_app.R
import com.example.cs124h_dining_app.data.DiningMenuRepository
import com.example.cs124h_dining_app.models.MenuRequest
import com.example.cs124h_dining_app.util.date
import com.example.cs124h_dining_app.util.meal

class DiningActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var checkIn: Button
    private lateinit var text: String
    private lateinit var spinner: Spinner
    private lateinit var stations: List<String>
    private var hallId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dining)
        spinner = findViewById<View>(R.id.spinner) as Spinner //anything with "spinner refers to the dropdown stations
        spinner.onItemSelectedListener = this
        checkIn = findViewById<View>(R.id.checkIn) as Button
        checkIn.setOnClickListener {
            startActivity(
                Intent(this, RatingActivity::class.java).apply {
                    putExtra("id", hallId)
                    putExtra("station", text)
                }
            )
            finish()
        }
        hallId = intent.getStringExtra("id")!!
        // Get and set stations
        DiningMenuRepository.getData(MenuRequest(hallId, date())) {
            stations = it[meal()]?.keys?.toList() ?: throw Exception("Failed")
            val stationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
            runOnUiThread {
                spinner.adapter = stationAdapter
            }
        }
    }

    //the next 2 methods are for the dropdown station options in order to select them and make them visible to the viewer
    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        text = adapterView.getItemAtPosition(i).toString()
        Toast.makeText(adapterView.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}