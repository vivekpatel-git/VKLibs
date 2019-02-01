package com.vk

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vk.libs.dialog.BottomListDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val singleAddress = ArrayList<String>()
        singleAddress.add("17 Fake Street")
        singleAddress.add("Phoney town")
        singleAddress.add("Makebelieveland")
        singleAddress.add("17 Fake Streetnew")
        singleAddress.add("Phoney townnew")
        singleAddress.add("Makebelievelandnew")
        singleAddress.add("17 Fake Streetnew1")
        singleAddress.add("Phoney townnew1")
        singleAddress.add("Makebelievelandnew1")


        openBottomDialog.setOnClickListener {
            showSlotsDialog(
                this,
                singleAddress,
                "Phoney town",
                BottomListDialog.Listener { slot -> Log.d("mytag", "Selected::$slot") })
        }

    }

    private fun showSlotsDialog(context: Activity, slots: List<String>, selected: String, listener: BottomListDialog.Listener<String>) {
        val dialog = object : BottomListDialog<String>(context) {
            override fun setItem(textView: TextView?, slot: String?, isSelected: Boolean) {
                try {
                    textView!!.text = slot
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                textView!!.setTextColor(if (isSelected) resources.getColor(R.color.AQUA) else resources.getColor(R.color.DARKLIGHTGRAY))
                textView.invalidate()
            }
        }

        dialog.setListener(listener)
        dialog.setTitle("Select a pick-up time:")
        dialog.setList(slots)
        dialog.setSelectedItem(selected)
        dialog.setCancelable(false)
        dialog.show()
    }
}
