package com.websarva.wings.android.busschedulemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class NewSchedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)
        val wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT

        val linearLayout = findViewById<LinearLayout>(R.id.newshedule)
        val editText = EditText(applicationContext)

        linearLayout.addView(editText, LinearLayout.LayoutParams(wrapContent,wrapContent))

    }

}