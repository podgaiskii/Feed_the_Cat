package com.example.feedthecat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.feedthecat.base.BaseFragment
import com.example.feedthecat.databinding.FragmentScoresBinding
import com.example.feedthecat.db.Score
import com.example.feedthecat.db.ScoresDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoresFragment : BaseFragment() {

    private lateinit var binding: FragmentScoresBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoresBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val scores: List<Score>

            withContext(Dispatchers.IO) {
                scores = ScoresDatabase.getInstance(requireContext()).scoresDao.getAll()
            }

            binding.rvScores.adapter = ScoresAdapter(requireContext(), scores)

        }
    }
}