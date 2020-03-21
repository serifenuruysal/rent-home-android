package com.ebayk.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ebayk.R
import com.ebayk.presentation.util.addFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fragmentsContainer != null) {
            if (savedInstanceState != null) {
                return
            }
            addFragment(R.id.fragmentsContainer, DataFragment(), true)
        }
    }
}
