package com.hdh.backpacker_task.ui.main.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.ui.base.BaseActivity
import com.hdh.backpacker_task.ui.main.fragment.MainFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.setBackgroundColor(Color.WHITE)
        pushFragment(MainFragment())
    }
}