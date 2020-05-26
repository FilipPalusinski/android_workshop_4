package com.example.gdgandroidwebinar4.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdgandroidwebinar4.R
import com.example.gdgandroidwebinar4.models.Pony
import kotlinx.android.synthetic.main.item_pony.view.*

class PonyAdapter : ListAdapter<Pony, PonyViewHolder>(PonyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PonyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pony, parent, false)
        return PonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PonyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object PonyDiffUtil : DiffUtil.ItemCallback<Pony>() {
        override fun areItemsTheSame(oldItem: Pony, newItem: Pony): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pony, newItem: Pony): Boolean {
            return oldItem == newItem
        }
    }
}

class PonyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(pony: Pony) {
        itemView.apply {
            ponyName.text = pony.name
            ponyAlias.text = if (pony.alias.isNullOrEmpty()) "" else "(${pony.alias})"
            ponySex.text = pony.sex.orEmpty()
            ponyOccupation.text = pony.occupation.orEmpty()
        }
    }
}