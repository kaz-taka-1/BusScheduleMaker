package com.websarva.wings.android.busschedulemaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class NewSchedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener(screencreate())

        //val wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT
        //val linearLayout = findViewById<LinearLayout>(R.id.newshedule)
        //val editText = EditText(applicationContext)
        //linearLayout.addView(editText, LinearLayout.LayoutParams(wrapContent,wrapContent))

    }
    //ボタンクリック
    private inner class screencreate(): View.OnClickListener{
        override fun onClick(view: View?) {
            val etTitle = findViewById<EditText>(R.id.title).text.toString()
            val etDestination = findViewById<EditText>(R.id.destination).text.toString()
            val etBusStop = findViewById<EditText>(R.id.bus_stop).text.toString()
            val etFirstTrainTime = findViewById<EditText>(R.id.first_train_time)
            val etLastTrainTime = findViewById<EditText>(R.id.last_train_time)

        }

    }

    //時刻表の自動作成


}