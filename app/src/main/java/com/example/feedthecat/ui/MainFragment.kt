package com.example.feedthecat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.feedthecat.R
import com.example.feedthecat.base.BaseFragment
import com.example.feedthecat.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val host = childFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController = host.navController
        navController.graph = navController.navInflater.inflate(R.navigation.navigation_main)
        binding.bnvMain.setupWithNavController(host.navController)
    }
}