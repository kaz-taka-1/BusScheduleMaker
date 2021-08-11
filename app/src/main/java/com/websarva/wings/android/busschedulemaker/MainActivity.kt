package com.websarva.wings.android.busschedulemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                val dialogFragment = NewScheduleDialog()
                dialogFragment.show(supportFragmentManager,"NewScheduleDialog")
            }
        }
        return returnVal
    }

}