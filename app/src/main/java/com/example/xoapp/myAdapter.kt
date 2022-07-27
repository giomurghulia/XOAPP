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
            if (item.icon != 0) {
                binding.iconButton.setImageResource(item.icon)
            }

            binding.iconButton.setOnClickListener {
                callBack?.onItemClick(item)
            }
        }

    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    interface CallBack {
        fun onItemClick(item: Item)
    }
}