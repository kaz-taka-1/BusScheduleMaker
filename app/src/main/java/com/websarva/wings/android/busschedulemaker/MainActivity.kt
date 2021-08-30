package com.websarva.wings.android.busschedulemaker

import android.content.Intent
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var busScheduleId = 1
        val _helper = DatabaseHelper(this@MainActivity)
        val db = _helper.writableDatabase
        var sql = "SELECT * FROM busschedules WHERE _id = ${busScheduleId}"
        val cursor =db.rawQuery(sql,null)
        var destination = ""
        var busStop = ""
        var firstTrainTime = 0
        var lastTrainTime = 0

        createSpinner(db)

        while(cursor.moveToNext()){
            val idxDestination = cursor.getColumnIndex("destination")
            destination = cursor.getString(idxDestination)
            val idxBusStop = cursor.getColumnIndex("busStop")
            busStop = cursor.getString(idxBusStop)
            val idxFirstTrainTime = cursor.getColumnIndex("firstTrainTime")
            firstTrainTime = cursor.getInt(idxFirstTrainTime)
            val idxLastTrainTime = cursor.getColumnIndex("lastTrainTime")
            lastTrainTime = cursor.getInt(idxLastTrainTime)
        }


        val tvTitle = findViewById<Spinner>(R.id.tvTitle)
        //tvTitle.setText(title)
        val tvDestination = findViewById<TextView>(R.id.tvDestination)
        tvDestination.setText(destination)
        val tvBusStop = findViewById<TextView>(R.id.tvBusStop)
        tvBusStop.setText(busStop)
        val tableLayout = findViewById<View>(R.id.newScheduleLayout) as ViewGroup
        tableLayout.removeAllViews()
        var count = 0

        var timeTableId = busScheduleId
        val sql2 = "SELECT * FROM timetable WHERE _id = ${timeTableId}"
        val cursor2 =db.rawQuery(sql2,null)
        val minutes:MutableList<String> = mutableListOf()
        minutes.add("0")
        var minute = ""

        while (cursor2.moveToNext()) {
            for(i in 1 until 24) {
                val clock = "o" + i.toString()
                minute = ""
                val idxminute = cursor2.getColumnIndex(clock)
                minute = cursor2.getString(idxminute)
                minutes.add(minute)
            }
        }

        for (i in  firstTrainTime until (lastTrainTime + 1) ) {
            val view = getLayoutInflater().inflate(R.layout.output_layout, tableLayout)
            val tr = tableLayout.getChildAt(count) as TableRow
            var tvHour = ((tr.getChildAt(0)) as TextView)
            tvHour.setText(i.toString())
            val evMinute = ((tr.getChildAt(1)) as TextView)
            val tvminute = view.findViewById<TextView>(R.id.tvminute)
            tvminute.setText(minutes[i])
            tvminute.setId(ViewCompat.generateViewId())
            count++
        }

    }


    //オプションメニューの埋め込み
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_list,menu)
        return true
    }

    //メニューアイテムのクリック
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal =true
        when(item.itemId){
            R.id.new_schedule_item -> {
                /*val dialogFragment = NewScheduleDialog()
                dialogFragment.show(supportFragmentManager,"NewScheduleDialog")*/
                val intent = Intent(this@MainActivity,NewSchedule::class.java)
                startActivity(intent)
            }
        }
        return returnVal
    }
    private fun createSpinner(db:SQLiteDatabase){
        val recodeCount = DatabaseUtils.queryNumEntries(db, "busschedules")
        val splist2: MutableList<MutableMap<String,String>> = mutableListOf()
        var title = ""
        var id =""
        val titles:MutableList<String> = mutableListOf()
        val ids:MutableList<String> = mutableListOf()
        for (i in 1 until recodeCount+1) {
            val sql1 = "SELECT * FROM busschedules WHERE _id = ${i}"
            val cursor1 =db.rawQuery(sql1,null)
            while(cursor1.moveToNext()) {
                val idnum = cursor1.getColumnIndex("_id")
                val idxTitle = cursor1.getColumnIndex("title")
                title = cursor1.getString(idxTitle)
                id = cursor1.getString(idnum)
                titles.add(title)
                ids.add(id)
                //var splist= mutableMapOf("id" to id.toString(),"title" to title)
                //splist2.add(splist)
                Log.i("table", titles.toString())
                Log.i("table", ids.toString())
            }
        }

        val adapter = SpinnerAdapter(this.getApplicationContext(),
            R.layout.spinner_item, ids,titles);

        val tvTitle = findViewById<Spinner>(R.id.tvTitle) as Spinner
        tvTitle.adapter = adapter
    }


}