package com.websarva.wings.android.busschedulemaker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

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
            val newScheduleLinearLayout = findViewById<View>(R.id.newScheduleLayout)
            //val inputLayout = findViewById<View>(R.id.inputLayout) as ViewGroup


            val vg = findViewById<View>(R.id.newScheduleLayout) as ViewGroup
            for (i in 0 until 5 ) {
                getLayoutInflater().inflate(R.layout.input_layout, vg)
                val tr = vg.getChildAt(i) as TableRow;
                //textviewに文字を格納
                ((tr.getChildAt(0)) as TextView).setText(i.toString())
                //buttonの動的追加と押されたときの処理の記載
                ((tr.getChildAt(1)) as TextView)?.setText(i.toString())
            }
            /*val tableLayout = TableLayout(applicationContext).also {
                //tableLayout.setBackgroundColor(Color.parseColor("#4CAF50"))
                val tableRow1 = TableRow(applicationContext).also {
                    //android:background="#000000"
                    val textView = TextView(applicationContext)
                    textView.text = etTitle
                    val editView = EditText(applicationContext)
                    //editView.text = etDestination
                    it.addView(textView)
                    it.addView(editView)
                }
                newScheduleLinearLayout.addView(tableRow1)
            //}*/
            //getLayoutInflater().inflate(R.layout.table, inputLayout)
            //newScheduleLinearLayout.addView(inputLayout,)

        }

    }

    //時刻表の自動作成


}