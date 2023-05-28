package com.example.dz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dz.R
import com.example.dz.databinding.SubjectItemBinding
import com.example.dz.firebase.model.Homework

class RaspAdapter(private val listRead: ArrayList<Homework>,
                  private val listener: SubjectClickListener): RecyclerView.Adapter<RaspAdapter.ReadHolder>() {

    inner class ReadHolder(item: View, private val binding: SubjectItemBinding): RecyclerView.ViewHolder(item){
        fun bind(new: Homework) = with(binding){
            subjectHomework.text = new.name
            dataTime.text = new.date
            briefHomework.text = new.theme

            cardView.setOnClickListener {
                listener.onClick(new)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadHolder {
        val binding = SubjectItemBinding.inflate(LayoutInflater.from(parent.context))
        return ReadHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ReadHolder, position: Int) {
        holder.bind(listRead[position])
    }

    override fun getItemCount(): Int {
        return listRead.size
    }

    interface SubjectClickListener {
        fun onClick(model: Homework)
    }

}