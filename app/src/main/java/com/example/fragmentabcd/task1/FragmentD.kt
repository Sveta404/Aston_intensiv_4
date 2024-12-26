package com.example.fragmentabcd.task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentabcd.R

class FragmentD : Fragment(R.layout.fragment_d) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBackToB: Button = view.findViewById(R.id.button_back_to_b)

        buttonBackToB.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentD_to_fragmentB)  // Сброс цепочки
        }
    }
}
