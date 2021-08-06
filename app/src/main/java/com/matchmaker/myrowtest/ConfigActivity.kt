package com.matchmaker.myrowtest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.edit

class ConfigActivity : AppCompatActivity() {
    var pref: SharedPreferences? = null
    var editRow : EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        pref = getSharedPreferences("myrowtest", MODE_PRIVATE)
        val rowCount = pref?.getInt("ROW_COUNT",5)
        editRow = findViewById<AppCompatEditText>(R.id.edt_limit)
        editRow?.setText(rowCount.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = editRow?.text?.toString()?.trim()?.toInt()?:5
        pref?.edit { putInt("ROW_COUNT", count) }
    }
}