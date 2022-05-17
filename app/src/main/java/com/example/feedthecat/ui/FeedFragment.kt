package com.example.feedthecat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import com.example.feedthecat.base.BaseFragment
import com.example.feedthecat.databinding.FragmentFeedBinding
import com.example.feedthecat.db.Score
import com.example.feedthecat.db.ScoresDatabase
import kotlinx.coroutines.*

class FeedFragment : BaseFragment() {

    private lateinit var binding: FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater)

        binding.btnFeed.setOnClickListener {
            binding.satiety++

            if (binding.satiety % 15 == 0) {
                val ivWidth = binding.ivCat.width
                val ivHeight = binding.ivCat.height
                val scaleX = (((ivWidth + 1)..(ivWidth * 2)).random() / ivWidth).toFloat()
                val scaleY = (((ivHeight + 1)..(ivHeight * 2)).random() / ivHeight).toFloat()
                binding.ivCat.animate()
                    .scaleXBy(scaleX)
                    .scaleYBy(scaleY)
                    .setInterpolator(LinearInterpolator())
                    .setDuration((500..1000).random().toLong())
                    .withEndAction {
                        binding.ivCat.animate()
                            .scaleXBy(-scaleX)
                            .scaleYBy(-scaleY)
                            .setInterpolator(BounceInterpolator())
                            .setDuration((1000..2000).random().toLong())
                            .start()
                    }
                    .start()
            }
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()

        if (binding.satiety > 0) {
            runBlocking {
                withContext(Dispatchers.IO) {
                    ScoresDatabase.getInstance(requireContext())
                        .scoresDao.insert(
                            Score(
                                score = binding.satiety
                            )
                        )
                }
            }
        }
    }
}