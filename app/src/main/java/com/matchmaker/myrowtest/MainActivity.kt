package com.matchmaker.myrowtest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    var listCheckbox: ListView? = null
    var btnConfig: Button? = null
    var btnSubmit: Button? = null
    var pref: SharedPreferences? = null

    var listAdapter: CheckBoxesBaseAdapter? = null

    var selectedCheckBoxes = HashMap<String, ArrayList<String>>()
    var newList = arrayListOf<String?>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences("myrowtest", MODE_PRIVATE)
        listCheckbox = findViewById<ListView>(R.id.list_checkbox)

        btnSubmit = findViewById<Button>(R.id.btn_submit)
        btnConfig = findViewById<Button>(R.id.btn_config)

        btnSubmit?.setOnClickListener {
            Log.i("--------------" ,"---------------------------------------------------------")
            val selected = listAdapter?.getSelectedValue()
            selected?.forEach { s, arrayList ->
                val displayVal = s.toInt() + 1
                Log.i("----------" + "${displayVal} :", TextUtils.join(",", arrayList.toList()))
            }
            Log.i("--------------" ,"---------------------------------------------------------")
        }
        btnConfig?.setOnClickListener {
            val newIntent = Intent(this@MainActivity, ConfigActivity::class.java)
            startActivity(newIntent)
        }

    }


    override fun onResume() {
        super.onResume()
        listAdapter = CheckBoxesBaseAdapter(this, arrayListOf(), selectedCheckBoxes)
        listCheckbox?.adapter = listAdapter
        createArrayList()
    }

    fun createArrayList() {
        var rowCount = pref?.getInt("ROW_COUNT", 5) ?: 5
        rowCount--
        newList.clear()
        selectedCheckBoxes.clear()
        for (i in 0..(rowCount)) {
            selectedCheckBoxes.put(i.toString(), arrayListOf())
            newList.add("")
        }
        listAdapter?.updateList(newList)
    }
}