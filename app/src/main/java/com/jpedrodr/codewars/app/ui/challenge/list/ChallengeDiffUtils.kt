package com.jpedrodr.codewars.app.ui.challenge.list

import androidx.recyclerview.widget.DiffUtil
import com.jpedrodr.codewars.app.model.CompletedChallenge

const val CHALLENGE_CONTENT_PAYLOAD = "CHALLENGE_CONTENT_PAYLOAD"

class ChallengeDiffUtils(
    private val oldItems: List<CompletedChallenge>,
    private val newItems: List<CompletedChallenge>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return newItem.name == oldItem.name && newItem.slug == oldItem.slug && newItem.completedAt == oldItem.completedAt && newItem.completedLanguages == oldItem.completedLanguages
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val payloads = mutableListOf<String>()

        if (!areContentsTheSame(oldItemPosition, newItemPosition)) {
            payloads.add(CHALLENGE_CONTENT_PAYLOAD)
        }

        return payloads
    }
}