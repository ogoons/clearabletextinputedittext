package com.ogoons.clearabletextinputedittextdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ogoons.clearabletextinputedittext.ClearableTextInputEditText
import com.ogoons.clearabletextinputedittext.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etClearable = findViewById<ClearableTextInputEditText>(R.id.et_clearable)
        etClearable.clearButtonDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cancel_24px)
    }
}