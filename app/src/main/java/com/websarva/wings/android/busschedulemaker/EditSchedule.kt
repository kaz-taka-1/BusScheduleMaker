package com.websarva.wings.android.busschedulemaker

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class EditSchedule : AppCompatActivity(), DeleteDialogFlagment.NoticeDialogListener {

    private val _helper = DatabaseHelper(this@EditSchedule)
    var editTextIds: MutableList<Int> = mutableListOf()
    var intFTT = 0
    var intLTT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        val busScheduleId = intent.getIntExtra("busScheduleId",0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener(screencreate())
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener(onSaveButtonClick())
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener(onBackButtonClick())

        val context = this
        val view1 = findViewById<View>(R.id.edit)
        val sp = spcall(context, view1)
        sp.call3()
        val tvTitle = findViewById<Spinner>(R.id.sptitle)
        tvTitle.setOnItemSelectedListener(ItemSelectedListener(context, view1,busScheduleId))
        Log.i("test1",editTextIds.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.delete_option,menu)
        return true
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val tvtitle = findViewById<TextView>(R.id.tvTitleItem)
        val title = tvtitle.text.toString()
        var returnVal =true
        when(item.itemId){
            R.id.delete_item -> {
                val dialogFragment = DeleteDialogFlagment(title)
                dialogFragment.show(supportFragmentManager,"DeleteScheduleDialog")
            }
        }
        return returnVal
    }
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val id = findViewById<TextView>(R.id.tvIdItem)
        val _id = id.text.toString().toLong()
        val db = _helper.writableDatabase
        val sqlDelete = "DELETE FROM busschedules WHERE _id =?"
        var stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1,_id)
        stmt.executeUpdateDelete()
        val sqlDelete2 = "DELETE FROM timetable WHERE _id =?"
        var stmt2 = db.compileStatement(sqlDelete2)
        stmt2.bindLong(1,_id)
        stmt2.executeUpdateDelete()
        Toast.makeText(this,"消去しました",Toast.LENGTH_SHORT).show()
        finish()
    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    private inner class screencreate() : View.OnClickListener {
        override fun onClick(view: View?) {
            val etTitle = findViewById<EditText>(R.id.title)
            val title = etTitle.getText().toString()
            val etDestination = findViewById<EditText>(R.id.destination)
            val destination = etDestination.getText().toString()
            val etBusStop = findViewById<EditText>(R.id.bus_stop)
            val busStop = etBusStop.getText().toString()
            val etFirstTrainTime = findViewById<EditText>(R.id.first_train_time)
            val firstTrainTime = etFirstTrainTime.getText().toString()
            val etLastTrainTime = findViewById<EditText>(R.id.last_train_time)
            val lastTrainTime = etLastTrainTime.getText().toString()
            val btnSave = findViewById<Button>(R.id.btnSave)
            val tableLayout = findViewById<View>(R.id.timetable) as ViewGroup
            if(title == "" || destination == "" || busStop == "" || firstTrainTime == "" || lastTrainTime == "" ) {
                Toast.makeText(this@EditSchedule,R.string.errormessage1,Toast.LENGTH_SHORT).show()
                btnSave.isEnabled = false
            }else{
                if(0 < firstTrainTime.toInt() && lastTrainTime.toInt() < 25) {
                    if (firstTrainTime.toInt() < lastTrainTime.toInt()) {
                        intFTT = firstTrainTime.toInt()
                        intLTT = lastTrainTime.toInt()
                        tableLayout.removeAllViews()
                        var count = 0
                        editTextIds = mutableListOf()
                        for (i in etFirstTrainTime.text.toString()
                            .toInt() until (etLastTrainTime.text.toString().toInt() + 1)) {
                            val view =
                                getLayoutInflater().inflate(R.layout.input_layout, tableLayout)
                            val tr = tableLayout.getChildAt(count) as TableRow
                            var tvHour = ((tr.getChildAt(0)) as TextView)
                            tvHour.setText(i.toString())
                            var evMinute = ((tr.getChildAt(1)) as EditText)
                            val minute = view.findViewById<EditText>(R.id.minute)
                            editTextIds.add(ViewCompat.generateViewId())
                            minute.setId(editTextIds[count])
                            count++
                        }
                        btnSave.isEnabled = true
                    }else{
                        tableLayout.removeAllViews()
                        Toast.makeText(this@EditSchedule,R.string.errormessage3,Toast.LENGTH_SHORT).show()
                        btnSave.isEnabled = false
                    }
                }else{
                    tableLayout.removeAllViews()
                    Toast.makeText(this@EditSchedule,R.string.errormessage2,Toast.LENGTH_SHORT).show()
                    btnSave.isEnabled = false
                }
            }
        }
    }
    private inner class onBackButtonClick(): View.OnClickListener {
        override fun onClick(view: View?) {
            finish()
        }
    }

    private inner class onSaveButtonClick() : View.OnClickListener {
        override fun onClick(view: View?) {
            val id = findViewById<TextView>(R.id.tvIdItem)
            val _id = id.text.toString().toLong()
            val etTitle = findViewById<EditText>(R.id.title)
            val title = etTitle.text.toString()
            val etDestination = findViewById<EditText>(R.id.destination)
            val destination = etDestination.text.toString()
            val etBusStop = findViewById<EditText>(R.id.bus_stop)
            val busStop = etBusStop.text.toString()
            val etFirstTrainTime = findViewById<EditText>(R.id.first_train_time)
            val firstTrainTime = etFirstTrainTime.text.toString().toLong()
            val etLastTrainTime = findViewById<EditText>(R.id.last_train_time)
            val lastTrainTime = etLastTrainTime.text.toString().toLong()
            val db = _helper.writableDatabase
            val sqlDelete = "DELETE FROM busschedules WHERE _id =?"
            var stmt = db.compileStatement(sqlDelete)
            stmt.bindLong(1,_id)
            stmt.executeUpdateDelete()
            val sqlInsert =
                "INSERT INTO busschedules (_id, title, destination, busStop, firstTrainTime, lastTrainTime) VALUES(?,?,?,?,?,?)"
            stmt = db.compileStatement(sqlInsert)
            stmt.bindLong(1,_id)
            stmt.bindString(2, title)
            stmt.bindString(3, destination)
            stmt.bindString(4, busStop)
            stmt.bindLong(5, intFTT.toLong())
            stmt.bindLong(6, intLTT.toLong())
            val executeInsert = stmt.executeInsert()

            val minutes: MutableList<String> = mutableListOf()
            var count = 0
            for (i in intFTT until (intLTT + 1)) {
                val minute = findViewById<EditText>(editTextIds[count])
                minutes.add(minute.text.toString())
                count++
            }
            val sqlDelete2 = "DELETE FROM timetable WHERE _id =?"
            var stmt2 = db.compileStatement(sqlDelete2)
            stmt2.bindLong(1,_id)
            stmt2.executeUpdateDelete()
            val sql2Insert =
                "INSERT INTO timetable (_id, o1, o2, o3, o4, o5, o6 , o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23, o24) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
            stmt2 = db.compileStatement(sql2Insert)
            stmt2.bindLong(1,_id)
            count = 0
            for (i in intFTT until (intLTT + 1)) {
                stmt2.bindString(i + 1, minutes[count])
                count++
            }
            stmt2.executeInsert()
            finish()
        }
    }
    inner class ItemSelectedListener(val context: Context, val view1: View, var busScheduleId: Int) :AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val tvid = view?.findViewById<TextView>(R.id.tvIdItem)
            val id = tvid?.text.toString()
            busScheduleId = id.toInt()
            val sp = spcall(context,view1)
            sp.busScheduleId
            sp.call1(busScheduleId)
            sp.call2(busScheduleId)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
        }
    }

    open inner class spcall(val context: Context, val view1: View){
        val _helper = DatabaseHelper(context)
        val db = _helper.writableDatabase
        var title =""
        var destination = ""
        var busStop = ""
        var firstTrainTime = 0
        var lastTrainTime = 0
        var busScheduleId = 1
        fun call3(){
            val recodeCount = DatabaseUtils.queryNumEntries(db, "busschedules")
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
                }
            }
            val adapter = SpinnerAdapter(context, R.layout.spinner_item, ids,titles);
            val tvTitle = view1.findViewById<Spinner>(R.id.sptitle)
            tvTitle.adapter = adapter
        }

        open fun call1(busScheduleId:Int) {
            var sql = "SELECT * FROM busschedules WHERE _id = ${busScheduleId}"
            val cursor = db.rawQuery(sql, null)
            while (cursor.moveToNext()) {
                val idxTitle = cursor.getColumnIndex("title")
                title = cursor.getString(idxTitle)
                val idxDestination = cursor.getColumnIndex("destination")
                destination = cursor.getString(idxDestination)
                val idxBusStop = cursor.getColumnIndex("busStop")
                busStop = cursor.getString(idxBusStop)
                val idxFirstTrainTime = cursor.getColumnIndex("firstTrainTime")
                firstTrainTime = cursor.getInt(idxFirstTrainTime)
                val idxLastTrainTime = cursor.getColumnIndex("lastTrainTime")
                lastTrainTime = cursor.getInt(idxLastTrainTime)
            }
            intFTT = firstTrainTime
            intLTT = lastTrainTime
            val tvTitle = view1.findViewById<EditText>(R.id.title)
            tvTitle.setText(title)
            val tvDestination = view1.findViewById<EditText>(R.id.destination)
            tvDestination.setText(destination)
            val tvBusStop = view1.findViewById<EditText>(R.id.bus_stop)
            tvBusStop.setText(busStop)
            val tvFirstTrainTime = view1.findViewById<EditText>(R.id.first_train_time)
            tvFirstTrainTime.setText(firstTrainTime.toString())
            val tvLastTrainTime = view1.findViewById<EditText>(R.id.last_train_time)
            tvLastTrainTime.setText(lastTrainTime.toString())
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
                    if (minute != null) {
                        minutes.add(minute)
                    }
                }
            }

            for (i in  intFTT until (intLTT + 1) ) {
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                inflater.inflate(R.layout.input_layout, tableLayout)
                val tr = tableLayout.getChildAt(count) as TableRow
                var tvHour = ((tr.getChildAt(0)) as TextView)
                tvHour.setText(i.toString())
                val evMinute = ((tr.getChildAt(1)) as TextView)
                val tvminute = tableLayout.findViewById<EditText>(R.id.minute)
                tvminute.setText(minutes[i])
                editTextIds.add(ViewCompat.generateViewId())
                tvminute.setId(editTextIds[count])
                count++
            }
        }
    }
}
