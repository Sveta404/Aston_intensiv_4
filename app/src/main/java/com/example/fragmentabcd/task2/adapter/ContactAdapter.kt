package com.example.contacts.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ContactAdapter(
    onClickContact: (id: Int) -> Unit
) : ListDelegationAdapter<List<Any>>() {

    init {
        delegatesManager.addDelegate(ContactListItemAdapterDelegate(onClickContact))
    }
}


