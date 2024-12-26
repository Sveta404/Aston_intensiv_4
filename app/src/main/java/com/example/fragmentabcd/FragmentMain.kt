package com.example.fragmentabcd

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentMain : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonTask1: Button = view.findViewById(R.id.button_task1)
        buttonTask1.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentA)
        }

        val buttonTask2: Button = view.findViewById(R.id.button_task2)
        buttonTask2.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_contact_list)
        }
    }
}