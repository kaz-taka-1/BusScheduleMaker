package com.websarva.wings.android.busschedulemaker

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat


class MainActivity : AppCompatActivity() {
    private val _helper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        val view1 = findViewById<View>(R.id.main)
        //val viewset = NewSchedule.viewset()
        val viewset = viewset(context,view1)
        viewset.call3()
        val tvTitle = findViewById<Spinner>(R.id.tvTitle)
        tvTitle.setOnItemSelectedListener(ItemSelectedListener(context,view1))
    }

    override fun onRestart() {
        super.onRestart()
        reload()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_list,menu)
        return true
    }
    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal =true
        when(item.itemId){
            R.id.new_schedule_item -> {
                val intent = Intent(this@MainActivity,NewSchedule::class.java)
                startActivity(intent)

            }
            R.id.edit_item -> {
                val id = findViewById<TextView>(R.id.tvIdItem)
                if(id != null){
                    val intent = Intent(this@MainActivity,EditSchedule::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@MainActivity,R.string.errormessage4,Toast.LENGTH_SHORT).show()
                }
            }
        }
        return returnVal
    }

    inner class ItemSelectedListener(val context: Context, val view1: View) :AdapterView.OnItemSelectedListener {
        var busScheduleId = 1

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val tvid = view?.findViewById<TextView>(R.id.tvIdItem)
            val id = tvid?.text.toString()
            busScheduleId = id.toInt()
            val sp = viewset(context,view1)
            sp.busScheduleId
            sp.call1(busScheduleId)
            sp.call2(busScheduleId)
        }
        override fun onNothingSelected(parent: AdapterView<*>) {
        }
    }


   open inner class viewset(val context: Context, val view1: View){

       val db = _helper.writableDatabase
       var destination = ""
       var busStop = ""
       var firstTrainTime = 0
       var lastTrainTime = 0
       var busScheduleId = 1
       var maxrecode = 1
       fun call3(){
           val recodeCount = "SELECT * FROM busschedules WHERE _id = (SELECT MAX(_id) FROM busschedules)"
           val cursor =db.rawQuery(recodeCount,null)
           while(cursor.moveToNext()) {
               val idmax = cursor.getColumnIndex("_id")
               val max = cursor.getInt(idmax)
               maxrecode = max
           }

           val splist2: MutableList<MutableMap<String,String>> = mutableListOf()
           var title = ""
           var id =""
           val titles:MutableList<String> = mutableListOf()
           val ids:MutableList<String> = mutableListOf()
           for (i in 1 until maxrecode + 1) {
               val sql1 = "SELECT * FROM busschedules WHERE _id = ${i}"
               val cursor1 =db.rawQuery(sql1,null)
               while(cursor1.moveToNext()) {
                   val idnum = cursor1.getColumnIndex("_id")
                   val idxTitle = cursor1.getColumnIndex("title")
                   title = cursor1.getString(idxTitle)
                   id = cursor1.getString(idnum)
                   titles.add(title)
                   ids.add(id)
               }
           }
           val adapter = SpinnerAdapter(context, R.layout.spinner_item, ids,titles);
           val tvTitle = view1.findViewById<Spinner>(R.id.tvTitle)
           tvTitle.adapter = adapter

       }

       open fun call1(busScheduleId:Int) {
           var sql = "SELECT * FROM busschedules WHERE _id = ${busScheduleId}"
           val cursor = db.rawQuery(sql, null)
           while (cursor.moveToNext()) {
               val idxDestination = cursor.getColumnIndex("destination")
               destination = cursor.getString(idxDestination)
               val idxBusStop = cursor.getColumnIndex("busStop")
               busStop = cursor.getString(idxBusStop)
               val idxFirstTrainTime = cursor.getColumnIndex("firstTrainTime")
               firstTrainTime = cursor.getInt(idxFirstTrainTime)
               val idxLastTrainTime = cursor.getColumnIndex("lastTrainTime")
               lastTrainTime = cursor.getInt(idxLastTrainTime)
           }
           val tvDestination = view1.findViewById<TextView>(R.id.tvDestination)
           tvDestination.setText(destination)
           val tvBusStop = view1.findViewById<TextView>(R.id.tvBusStop)
           tvBusStop.setText(busStop)
       }

       open fun call2(busScheduleId:Int){
           val tableLayout = view1.findViewById<View>(R.id.timetable) as ViewGroup
           tableLayout.removeAllViews()
           var count = 0

           var timeTableId = busScheduleId
           val sql2 = "SELECT * FROM timetable WHERE _id = ${timeTableId}"
           val cursor2 =db.rawQuery(sql2,null)
           val minutes:MutableList<String> = mutableListOf()
           minutes.add("0")
           var minute = ""

           while (cursor2.moveToNext()) {
               for(i in 1 until 25) {
                   val clock = "o" + i.toString()
                   minute = ""
                   val idxminute:Int? = cursor2.getColumnIndex(clock)
                   minute = idxminute?.let { cursor2.getString(it) }.toString()
                   minutes.add(minute)

               }
           }
           for (i in  firstTrainTime until (lastTrainTime +1) ) {
               val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
               inflater.inflate(R.layout.output_layout, tableLayout)
               val tr = tableLayout.getChildAt(count) as TableRow
               var tvHour = ((tr.getChildAt(0)) as TextView)
               tvHour.setText(i.toString())
               val evMinute = ((tr.getChildAt(1)) as TextView)
               val tvminute = tableLayout.findViewById<TextView>(R.id.tvminute)
               tvminute.setText(minutes[i])
               tvminute.setId(ViewCompat.generateViewId())
               count++
           }

       }
   }
    private fun reload() {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }
}