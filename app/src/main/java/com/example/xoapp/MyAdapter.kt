package com.example.xoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xoapp.databinding.LayoutItemBinding

class MyAdapter : ListAdapter<Item, MyAdapter.ItemViewHolder>(MyDiffUtil()) {
    private var callBack: CallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class ItemViewHolder(
        private val binding: LayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            when (item.player) {
                1 -> binding.iconButton.setImageResource(R.drawable.ic_x)
                2 -> binding.iconButton.setImageResource(R.drawable.ic_o)
            }

            binding.iconButton.setOnClickListener {
                callBack?.onItemClick(adapterPosition)
            }
        }
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    interface CallBack {
        fun onItemClick(itemPosition: Int)
    }
}