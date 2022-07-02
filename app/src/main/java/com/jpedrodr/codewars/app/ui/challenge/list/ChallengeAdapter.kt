package com.jpedrodr.codewars.app.ui.challenge.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jpedrodr.codewars.app.model.CompletedChallenge
import com.jpedrodr.codewars.app.ui.challenge.ChallengeDateFormatter
import com.jpedrodr.codewars.databinding.ChallengeListItemBinding

class ChallengeAdapter : RecyclerView.Adapter<ChallengeAdapter.ChallengeItemViewHolder>() {

    private var items: MutableList<CompletedChallenge> = mutableListOf()
    var onItemClick: ((CompletedChallenge) -> Unit)? = null

    private val formatter = ChallengeDateFormatter()

    fun setupItems(challenges: List<CompletedChallenge>) {
        val result = DiffUtil.calculateDiff(ChallengeDiffUtils(items, challenges))
        result.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(challenges)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeItemViewHolder {
        val binding =
            ChallengeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("test009", "onCreateViewHolder")
        return ChallengeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeItemViewHolder, position: Int) {
        val item = items[position]
        holder.setupItem(item)
        Log.d("test009", "onBindViewHolder 1")
    }

    override fun onBindViewHolder(
        holder: ChallengeItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.d("test009", "onBindViewHolder 2  - payloads=${payloads.size}")
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        val stringPayloads = getPayloads(payloads)
        Log.d("test009", "payloads=$payloads, stringPayloads=$stringPayloads")

        val item = items[position]

        if (stringPayloads.contains(CHALLENGE_CONTENT_PAYLOAD)) {
            holder.setupItem(item)
        }
    }


    private fun getPayloads(payloads: MutableList<Any>): List<String> {
        val pl = payloads.filterIsInstance<List<*>>()
        return pl.flatten().filterIsInstance<String>()
    }

    override fun getItemCount(): Int = items.size

    inner class ChallengeItemViewHolder(private val binding: ChallengeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setupItem(item: CompletedChallenge) = with(binding) {
            challengeItemNameTv.text = item.name
            challengeItemSlugTv.text = item.slug

            challengeItemCompletedAtTv.text = formatter.format(item.completedAt)
            challengeItemCompletedLanguageTv.text = item.completedLanguages.joinToString()
            challengeItemCard.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}