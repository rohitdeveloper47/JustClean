package com.dashboard.justclean.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dashboard.justclean.R
import com.dashboard.justclean.ui.fragment.PostFragment

class HomeActivity : AppCompatActivity(){
    protected var appToolbar: Toolbar? = null
    protected var toobarTitle: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        appToolbar = findViewById(R.id.toolbar)
        toobarTitle = findViewById(R.id.middleTitle)
        if (appToolbar != null) {
            setSupportActionBar(appToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
        toobarTitle!!.text = getString(R.string.app_name)
        showFragment()
    }

    private fun showFragment() {

        supportFragmentManager.beginTransaction().replace(R.id.post_fragment, PostFragment()).commit()

    }

}