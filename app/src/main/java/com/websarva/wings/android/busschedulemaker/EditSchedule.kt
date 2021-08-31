package com.websarva.wings.android.busschedulemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat

class EditSchedule : AppCompatActivity() {
    private val _helper = DatabaseHelper(this@EditSchedule)
    var editTextIds: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)
        val bt1 = findViewById<Button>(R.id.bt1)
        bt1.setOnClickListener(screencreate())
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener(onSaveButtonClick())

    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

    //時刻入力欄作成ボタンをクリックした時の処理の記述
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

            if (title == "" || destination == "" || busStop == "" || firstTrainTime == "" || lastTrainTime == "") {
                Toast.makeText(this@EditSchedule, R.string.errormessage1, Toast.LENGTH_SHORT).show()
            } else {
                if (0 < firstTrainTime.toInt() && lastTrainTime.toInt() < 25) {
                    if (firstTrainTime.toInt() < lastTrainTime.toInt()) {
                        val tableLayout = findViewById<View>(R.id.newScheduleLayout) as ViewGroup
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
                        val btnSave = findViewById<Button>(R.id.btnSave)
                        btnSave.isEnabled = true
                    } else {
                        Toast.makeText(
                            this@EditSchedule,
                            R.string.errormessage3,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@EditSchedule, R.string.errormessage2, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private inner class onSaveButtonClick() : View.OnClickListener {
        override fun onClick(view: View?) {
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
            val sqlInsert =
                "INSERT INTO busschedules (_id, title, destination, busStop, firstTrainTime, lastTrainTime) VALUES(?,?,?,?,?,?)"
            val stmt = db.compileStatement(sqlInsert)
            stmt.bindString(2, title)
            stmt.bindString(3, destination)
            stmt.bindString(4, busStop)
            stmt.bindLong(5, firstTrainTime)
            stmt.bindLong(6, lastTrainTime)
            val executeInsert = stmt.executeInsert()

            val intFTT = firstTrainTime.toInt()
            val intLTT = lastTrainTime.toInt()
            val minutes: MutableList<String> = mutableListOf()
            var count = 0
            for (i in intFTT until (intLTT + 1)) {
                val minute = findViewById<EditText>(editTextIds[count])
                minutes.add(minute.text.toString())
                count++
            }

            val sql2Insert =
                "INSERT INTO timetable (_id, o1, o2, o3, o4, o5, o6 , o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23, o24) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
            val stmt2 = db.compileStatement(sql2Insert)

            count = 0
            for (i in intFTT until (intLTT + 1)) {
                stmt2.bindString(i + 1, minutes[count])
                count++
            }
            stmt2.executeInsert()

            finish()
        }
    }
}
