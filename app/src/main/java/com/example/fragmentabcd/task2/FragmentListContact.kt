package com.example.fragmentabcd.task2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.adapter.ContactAdapter
import com.example.fragmentabcd.R

class FragmentListContact : Fragment(R.layout.contact_list) {

    private lateinit var recyclerView: RecyclerView

    private val adapter = ContactAdapter(
        onClickContact = {
            val contact = contactsRepository.getContact(it) ?: return@ContactAdapter
            val action = FragmentListContactDirections.actionListToForm(contact)
            findNavController().navigate(action)
        }
    )

    private val contactsRepository = ContactsRepository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKey", viewLifecycleOwner) { key, bundle ->
            val result = bundle.getParcelable("contact") as? Contact
            if (result != null) {
                contactsRepository.editContact(result)
                renderContacts(contactsRepository.getContacts())
            }
        }

        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        renderContacts(contactsRepository.getContacts())
    }

    private fun renderContacts(contacts: List<Contact>) {
        adapter.items = contacts
        adapter.notifyDataSetChanged()
    }
}