package com.example.dz.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz.R
import com.example.dz.adapters.RaspAdapter
import com.example.dz.constants.Constants
import com.example.dz.databinding.FragmentHomeworkListBinding
import com.example.dz.firebase.model.Homework
import com.example.dz.viewModels.DataModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
var filter = "Всё"
class FragmentHomeworkList : Fragment() {

    private lateinit var mBase: DatabaseReference
    private lateinit var binding: FragmentHomeworkListBinding

    private val dataModel: DataModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeworkListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBase = FirebaseDatabase.getInstance().getReference(Constants.USER_KEY)
        mBase.addValueEventListener(onHomeworkChangeListener)

        val activity: AppCompatActivity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.title = "ИСиП-11"
    }

    private val onHomeworkChangeListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val homeworkList: ArrayList<Homework> = arrayListOf()
            for(homeworkSnapshot in snapshot.children){
                val homework = homeworkSnapshot.getValue(Homework::class.java)!!
                if(homework.name == filter || filter == "Всё") homeworkList.add(homework)
                else{continue}
            }
            val rvAdapter = RaspAdapter(homeworkList, subjectClickListener)
            binding.homeworkRv.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        override fun onCancelled(error: DatabaseError) {}

    }
    private val subjectClickListener = object : RaspAdapter.SubjectClickListener {
        override fun onClick(model: Homework) {
            Log.d(Constants.LOG_KEY, model.toString())
            dataModel.nameItem.value = model.name
            dataModel.description.value = model.description

            findNavController().navigate(R.id.action_fragment_rasp2_to_fragmentBigHomeWork)
        }
    }

}