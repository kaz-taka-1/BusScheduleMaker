package com.websarva.wings.android.busschedulemaker

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class NewScheduleDialog:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.tv_new_schedule)
            builder.setMessage(R.string.tv_title)
            builder.setMessage(R.string.tv_destination)
            builder.setMessage(R.string.tv_bus_stop)
            builder.setMessage(R.string.tv_operational_hour)
            builder.setNegativeButton(R.string.bt_thx_ng,DialogButtonClickListener())
            builder.setNegativeButton(R.string.bt_thx_ok,DialogButtonClickListener())
            builder.create()
        }
        return dialog ?: throw IllegalStateException("アクテビティがnullです")
    }

    private inner class DialogButtonClickListener:DialogInterface.OnClickListener{
        override fun onClick(dialog:DialogInterface,which:Int){
            var msg =""
            when(which){
                DialogInterface.BUTTON_POSITIVE->
                    msg = getString(R.string.bt_thx_ng)
                DialogInterface.BUTTON_NEGATIVE->
                    msg = getString(R.string.bt_thx_ok)
            }
            Toast.makeText(activity,msg, Toast.LENGTH_LONG).show()
        }
    }
}