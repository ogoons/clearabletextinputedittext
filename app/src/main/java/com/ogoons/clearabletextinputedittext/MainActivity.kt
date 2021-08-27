package com.ogoons.clearabletextinputedittext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etClearable = findViewById<ClearableTextInputEditText>(R.id.et_clearable)
        etClearable.clearButtonDrawable = ContextCompat.getDrawable(this, R.drawable.ic_cancel_24px)
    }
}