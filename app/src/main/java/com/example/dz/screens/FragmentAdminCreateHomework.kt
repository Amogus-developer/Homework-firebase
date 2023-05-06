package com.example.dz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.dz.constants.Constants
import com.example.dz.databinding.FragmentAdminCreateHomeworkBinding
import com.example.dz.firebase.model.Homework
import com.example.dz.viewModels.DataModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FragmentAdminCreateHomework : Fragment() {

    private lateinit var binding: FragmentAdminCreateHomeworkBinding
    private lateinit var mBase: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdminCreateHomeworkBinding.inflate(inflater, container, false)
        mBase = FirebaseDatabase.getInstance().getReference(Constants.USER_KEY)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bCreate.setOnClickListener { addHomework() }
    }

    private fun addHomework(){
        val subjectName = binding.chooseSubjectSpinner.selectedItem.toString()
        val id = mBase.key.toString()
        val theme = binding.edTheme.text.toString()
        val date = binding.edDate.text.toString()
        val description = binding.edExercise.text.toString()

        if (validateFields()) {
            val user = Homework(id = id, name = subjectName, theme = theme, description = description, date = date)
            mBase.push().setValue(user)
            Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show()
        }

        /*if (!TextUtils.isEmpty(binding.edDate.text) &&
                !TextUtils.isEmpty(binding.edTheme.text) &&
                !TextUtils.isEmpty(binding.edExercise.text)) {

                mBase.push().setValue(user)
                Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }*/

    }

    private fun validateFields(): Boolean {
        return if (binding.edDate.text.isEmpty() || binding.edTheme.text.isEmpty() || binding.edExercise.text.isEmpty()) {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.chooseSubjectSpinner.selectedItem.toString() == "Выберите предмет") {
            Toast.makeText(requireContext(), "Выберите предмет", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

}