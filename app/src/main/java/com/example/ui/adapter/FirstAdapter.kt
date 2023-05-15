package com.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_3_lesson_1.databinding.ItemBinding
import com.example.android_3_lesson_1.model.Model

class FirstAdapter(
    val onItemClick: (model: Model) -> Unit
) : RecyclerView.Adapter<FirstAdapter.FirstViewHolder>() {

    private var list: List<Model> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listTwo: List<Model>) {
        this.list = listTwo
        notifyDataSetChanged()

    }

    inner class FirstViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(list[adapterPosition])
            }
        }

        fun onBind(model: Model) {
            binding.txt.text = model.name
            Glide.with(binding.image.context).load(model.name).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        return FirstViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}