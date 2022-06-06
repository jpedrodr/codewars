package com.jpedrodr.codewars.app.ui.challenge.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpedrodr.codewars.databinding.ChallengeListItemBinding
import com.jpedrodr.codewars.domain.model.CompletedChallenge

class ChallangeAdapter : RecyclerView.Adapter<ChallangeAdapter.ChallengeItemViewHolder>() {

    private var items: List<CompletedChallenge> = emptyList()

    fun setupItems(challenges: List<CompletedChallenge>) {
        items = challenges

        // TODO this should use diff utils for better performance
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeItemViewHolder {
        val binding =
            ChallengeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeItemViewHolder, position: Int) {
        val item = items[position]
        holder.setupItem(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ChallengeItemViewHolder(private val binding: ChallengeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setupItem(item: CompletedChallenge) = with(binding) {
            challengeItemNameTv.text = item.name
            challengeItemSlugLabel.text = item.slug
            challengeItemCompletedAtTv.text = item.completedAt.toString()
            challengeItemCompletedLanguageTv.text = item.completedLanguages.size.toString()
        }
    }
}