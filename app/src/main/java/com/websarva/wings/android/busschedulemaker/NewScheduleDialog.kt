package com.websarva.wings.android.busschedulemaker

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class NewScheduleDialog:DialogFragment() {
    //新規時刻表ダイアログの表示
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = it.layoutInflater.inflate(R.layout.new_busstop_dialog, null)
            builder.setView(inflater.inflate(R.layout.new_busstop_dialog, null))
            builder.setPositiveButton(R.string.bt_thx_ok,DialogButtonClickListener(view))
            builder.setNegativeButton(R.string.bt_thx_ng,DialogButtonClickListener(view))
            builder.create()
        }
        return dialog ?: throw IllegalStateException("アクテビティがnullです")
    }

   /*override fun onSaveInstanceState(outState: Bundle) {
        val editText = view.findViewById<EditText>(R.id.title)
        val intent =Intent(getContext(),NewSchedule::class.java)
        val text1 = editText.text.toString()
        intent.putExtra("text1",text1)
        startActivity(intent)
    }*/

    private inner class DialogButtonClickListener(view : View):DialogInterface.OnClickListener{
        //val view = view
        override fun onClick(dialog:DialogInterface,which:Int){
            var msg =""
            when(which){
                DialogInterface.BUTTON_NEGATIVE -> {
                    msg = getString(R.string.bt_thx_ng)
                    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
                }
                DialogInterface.BUTTON_POSITIVE ->{
                    val intent =Intent(getContext(),NewSchedule::class.java)
                    /*val editText = view.findViewById<TextView>(R.id.title)
                    val text1 = editText.text.toString()
                    if (text1 =="") {
                        Toast.makeText(activity, "何も入力されていません。", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(activity, text1, Toast.LENGTH_SHORT).show()
                    }
                    intent.putExtra("TEXT1",text1)*/
                    startActivity(intent)

                }

            }

        }
    }

}