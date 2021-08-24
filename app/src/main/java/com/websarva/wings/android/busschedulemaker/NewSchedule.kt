package com.websarva.wings.android.busschedulemaker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

class NewSchedule : AppCompatActivity() {

    private val _helper = DatabaseHelper(this@NewSchedule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener(screencreate())
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }
    //時刻入力欄作成ボタンをクリックした時の処理の記述
    private inner class screencreate(): View.OnClickListener{
        override fun onClick(view: View?) {
            val etTitle = findViewById<EditText>(R.id.title).text.toString()
            val etDestination = findViewById<EditText>(R.id.destination).text.toString()
            val etBusStop = findViewById<EditText>(R.id.bus_stop).text.toString()
            val etFirstTrainTime = findViewById<EditText>(R.id.first_train_time).text.toString().toInt()
            val etLastTrainTime = findViewById<EditText>(R.id.last_train_time).text.toString().toInt()
            val newScheduleLinearLayout = findViewById<View>(R.id.newScheduleLayout)

            val tableLayout = findViewById<View>(R.id.newScheduleLayout) as ViewGroup
            tableLayout.removeAllViews()
            var count = 0
            for (i in  etFirstTrainTime until (etLastTrainTime + 1) ) {
                getLayoutInflater().inflate(R.layout.input_layout, tableLayout)
                val tr = tableLayout.getChildAt(count) as TableRow
                ((tr.getChildAt(0)) as TextView).setText(i.toString())
                ((tr.getChildAt(1)) as EditText)
                count++
            }
        }
    }

    fun pnSaveButtonClick(view: View){
        val etTitle = findViewById<EditText>(R.id.title)
        val etDestination = findViewById<EditText>(R.id.destination)
        val etBusStop = findViewById<EditText>(R.id.bus_stop).text.toString()
        val etFirstTrainTime = findViewById<EditText>(R.id.first_train_time).text.toString()
        val etLastTrainTime = findViewById<EditText>(R.id.last_train_time).text.toString()
        val btnSave = findViewById<Button>(R.id.btnSave)
    }


}