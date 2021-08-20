package com.websarva.wings.android.busschedulemaker

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vg = findViewById<View>(R.id.tableLayout) as ViewGroup
        for (i in 0 until 5 ) {
            getLayoutInflater().inflate(R.layout.input_layout, vg)
            val tr = vg.getChildAt(i) as TableRow;
            //textviewに文字を格納
            ((tr.getChildAt(0)) as TextView).setText(i.toString())
            //buttonの動的追加と押されたときの処理の記載
            ((tr.getChildAt(1)) as TextView)?.setText(i.toString())
                //この中に処理を書きます
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


}