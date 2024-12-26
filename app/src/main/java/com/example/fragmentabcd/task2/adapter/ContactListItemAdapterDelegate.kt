package com.example.contacts.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentabcd.R
import com.example.fragmentabcd.task2.Contact
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class ContactListItemAdapterDelegate(
    private val onClickContact: (id: Int) -> Unit
) : AbsListItemAdapterDelegate<Contact, Any, ContactListItemAdapterDelegate.ContactViewHolder>() {

    public override fun isForViewType(item: Any, items: List<Any>, position: Int): Boolean {
        return item is Contact
    }

    public override fun onCreateViewHolder(parent: ViewGroup): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(item: Contact, vh: ContactViewHolder, payloads: List<Any>) {
        when (item.portrait) {
            is Contact.Image.ImageDrawable -> vh.portrait.setImageResource(item.portrait.resId)
            is Contact.Image.ImageUri -> vh.portrait.setImageURI(Uri.parse(item.portrait.uri))
        }

        vh.firstname.setText(item.firstname)
        vh.lastname.setText(item.lastname)
        vh.phoneNumber.setText(item.phoneNumber)
        vh.itemView.setOnClickListener {
            onClickContact.invoke(item.id)
        }
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portrait: ImageView = itemView.findViewById<View>(R.id.portraitImageView) as ImageView
        var firstname: TextView = itemView.findViewById<View>(R.id.firstnameTextView) as TextView
        var lastname: TextView = itemView.findViewById<View>(R.id.lastnameTextView) as TextView
        var phoneNumber: TextView = itemView.findViewById<View>(R.id.phoneTextView) as TextView
    }
}