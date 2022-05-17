package com.example.feedthecat.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feedthecat.R
import com.example.feedthecat.databinding.ListItemNoScoresBinding
import com.example.feedthecat.databinding.ListItemScoreBinding
import com.example.feedthecat.db.Score
import java.text.SimpleDateFormat
import java.util.*

class ScoresAdapter(
    private val context: Context,
    private val items: List<Score>
) : RecyclerView.Adapter<ScoresAdapter.ScoreHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isEmpty() -> ScoreViewType.EMPTY.value
            position == 0 -> ScoreViewType.HEADER.value
            else -> ScoreViewType.ITEM.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder =
        when (viewType) {
            ScoreViewType.EMPTY.value -> ScoreHolder.EmptyHolder(
                ListItemNoScoresBinding.inflate(inflater, parent, false)
            )
            ScoreViewType.HEADER.value -> ScoreHolder.HeaderHolder(
                ListItemScoreBinding.inflate(inflater, parent, false),
                context
            )
            else -> ScoreHolder.ItemHolder(
                ListItemScoreBinding.inflate(inflater, parent, false)
            )
        }

    override fun onBindViewHolder(holder: ScoreHolder, position: Int) {
        when (holder) {
            is ScoreHolder.HeaderHolder -> holder.bind()
            is ScoreHolder.ItemHolder -> holder.bind(items[position - 1])
        }
    }

    override fun getItemCount(): Int = items.size + 1

    sealed class ScoreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class EmptyHolder(
            binding: ListItemNoScoresBinding
        ) : ScoreHolder(binding.root)

        class HeaderHolder(
            private val binding: ListItemScoreBinding,
            private val context: Context
        ) : ScoreHolder(binding.root) {

            fun bind() {
                binding.id = context.getString(R.string.number)
                binding.date = context.getString(R.string.date)
                binding.score = context.getString(R.string.score)
            }

        }

        class ItemHolder(
            private val binding: ListItemScoreBinding
        ) : ScoreHolder(binding.root) {

            companion object {
                @SuppressLint("ConstantLocale")
                private val dateFormat = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
            }

            fun bind(item: Score) {
                binding.id = item.id.toString()
                binding.date = dateFormat.format(item.date)
                binding.score = item.score.toString()
            }

        }
    }
}

private enum class ScoreViewType(val value: Int) {
    EMPTY(0), HEADER(1), ITEM(2);
}