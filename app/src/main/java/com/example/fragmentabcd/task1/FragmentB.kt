package com.example.fragmentabcd.task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentabcd.R

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNext: Button = view.findViewById(R.id.button_next)
        val buttonBack: Button = view.findViewById(R.id.button_back)

        buttonNext.setOnClickListener {
            val action = FragmentBDirections.actionFragmentBToFragmentC("Hello Fragment C")
            findNavController().navigate(action)
        }

        buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
