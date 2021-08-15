package com.websarva.wings.android.busschedulemaker

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bt = findViewById<Button>(R.id.bt1)
        val listener = HelloListener()
        bt.setOnClickListener(listener)

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

    private inner class HelloListener(): View.OnClickListener{
        override fun onClick(view: View?) {
            val evText = findViewById<EditText>(R.id.a)
            val text1 = evText.text.toString()
            val intent = Intent(this@MainActivity,NewSchedule::class.java)
            intent.putExtra("TEXT1",text1)
            startActivity(intent)
        }
    }

}