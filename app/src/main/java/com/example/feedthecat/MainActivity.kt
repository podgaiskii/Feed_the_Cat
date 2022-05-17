package com.example.feedthecat

import android.view.View
import com.example.feedthecat.base.BaseActivity
import com.example.feedthecat.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    override val rootView: View
        get() = binding.root

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

}