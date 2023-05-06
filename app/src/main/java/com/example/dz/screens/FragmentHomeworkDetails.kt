package com.example.dz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.dz.databinding.FragmentHomeworkDetailsBinding
import com.example.dz.viewModels.DataModel

class FragmentHomeworkDetails : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var binding: FragmentHomeworkDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeworkDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.description.observe(viewLifecycleOwner) {
            binding.homeWork.text = it
        }
        dataModel.nameItem.observe(viewLifecycleOwner) {
            binding.bigSubject.text = it
        }
    }

}