package com.devloper.squad.punkbeer

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author gleb.maliborsky
 */
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = findViewById<RecyclerView>(R.id.test)
        a.setOnClickListener {
            Log.d("Ura", "Ura")
        }
    }
}

/*
@RequiresApi(Build.VERSION_CODES.M)
class Test1 : View.OnScrollChangeListener {
    override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        Log.d("Test", "Test1")
    }
}*/
