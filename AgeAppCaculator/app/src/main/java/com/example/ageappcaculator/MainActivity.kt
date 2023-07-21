package com.example.ageappcaculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    val MonthStr = listOf<String>("January", "February", "March", "April" , "May", "June", "July", "August")
    val Sample : TextView? = null
    init {
        Sample?.text = System.currentTimeMillis().toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val BtnDatePicker : Button = findViewById(R.id.BtnSelectDate)
        BtnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    fun clickDatePicker(){
        val myCalender =  Calendar.getInstance()
        val Year = myCalender.get(Calendar.YEAR)
        val Month = myCalender.get(Calendar.MONTH)
        val Day = myCalender.get(Calendar.DAY_OF_MONTH)
        val BirthPick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month , day ->
            Toast.makeText(this,"DATE UPDATED!!",Toast.LENGTH_LONG).show()
            val Birth : String =   day.toString() + "/"+  (month+1) + "/"+ year
            Birth?.let {
                val TvBirth : TextView = findViewById(R.id.TvSelectDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val selectedDate  = sdf.parse(Birth)
                TvBirth.setText(Birth)
                val TimeTillDate = selectedDate.time / 60000
                val TimeTillNow = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000
                val MinuteTillDate  = TimeTillNow - TimeTillDate
                //Toast.makeText(this,"TimeTillDate ${TimeTillDate.toString()} TimeTillNow ${TimeTillNow.toString()} MinuteTillDate ${MinuteTillDate.toString()}",Toast.LENGTH_LONG).show()
                val TvMinute : TextView = findViewById(R.id.TvMinuteTillDate)
                TvMinute?.text = MinuteTillDate.toString() + " MINUTES"
            }

        },Year,Month,Day)
        BirthPick.datePicker.maxDate = System.currentTimeMillis() - 86400000
        BirthPick.show()
    }
}