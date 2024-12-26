package com.example.fragmentabcd.task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentabcd.R

class FragmentC : Fragment(R.layout.fragment_c) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = view.findViewById(R.id.textView_message)
        val buttonNext: Button = view.findViewById(R.id.button_next)
        val buttonReset: Button = view.findViewById(R.id.button_reset)

        textView.text = arguments?.getString("message") ?: "No message"

        buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentC_to_fragmentD)
        }

        buttonReset.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentC_to_fragmentA)
        }
    }
}
