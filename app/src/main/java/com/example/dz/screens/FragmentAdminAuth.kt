package com.example.dz.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dz.R
import com.example.dz.databinding.FragmentAdminAuthBinding

class FragmentAdminAuth : Fragment() {

    private lateinit var binding: FragmentAdminAuthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdminAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminAuthButton.setOnClickListener {
            if (binding.enterPasswordAdminEditText.text.toString() == "адМин123"){
                findNavController().navigate(R.id.action_fragmentAdminPassword_to_fragmentAdminDoHomeWork)
            }
            else{
                Toast.makeText(requireContext(), "Неверный пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}