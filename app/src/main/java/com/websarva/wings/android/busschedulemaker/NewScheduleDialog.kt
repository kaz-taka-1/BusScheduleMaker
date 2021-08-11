package com.websarva.wings.android.busschedulemaker

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException
import kotlin.math.E


class NewScheduleDialog:DialogFragment() {
    //新規時刻表ダイアログの表示
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            builder.setView(inflater.inflate(R.layout.new_busstop_dialog, null))
            builder.setPositiveButton(R.string.bt_thx_ok,DialogButtonClickListener())
            builder.setNegativeButton(R.string.bt_thx_ng,DialogButtonClickListener())
            builder.create()
        }
        return dialog ?: throw IllegalStateException("アクテビティがnullです")
    }

    private inner class DialogButtonClickListener():DialogInterface.OnClickListener{
        override fun onClick(dialog:DialogInterface,which:Int){
            var msg =""
            when(which){
                DialogInterface.BUTTON_NEGATIVE -> {
                    msg = getString(R.string.bt_thx_ng)
                    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
                }
                DialogInterface.BUTTON_POSITIVE ->{
                    val intent =Intent(getContext(),NewSchedule::class.java)
                    startActivity(intent)
                    /*val title =
                    intent.putExtra("title",title)
                    intent.putExtra("destination",destination)
                    intent.putExtra("bus_stop",bus_stop)
                    intent.putExtra("first_train_time",first_train_time)
                    intent.putExtra("last_train_time",last_train_time)*/

                }

                        //Intent(getContext(),NewSchedule::class.java)

            }

        }
    }
}