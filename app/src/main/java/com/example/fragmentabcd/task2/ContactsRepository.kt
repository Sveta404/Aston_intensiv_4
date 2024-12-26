package com.example.fragmentabcd.task2

import com.example.fragmentabcd.R

class ContactsRepository {

    private val contacts = mutableListOf<Contact>(
        Contact(
            id = 1,
            portrait = Contact.Image.ImageDrawable(R.drawable.portrait1),
            firstname = "Егор",
            lastname = "Смирнов",
            phoneNumber = "89506116613"
        ),
        Contact(
            id = 2,
            portrait = Contact.Image.ImageDrawable(R.drawable.portrait2),
            firstname = "Елена",
            lastname = "Разина",
            phoneNumber = "89506116614"
        ),
        Contact(
            id = 3,
            portrait = Contact.Image.ImageDrawable(R.drawable.portrait3),
            firstname = "Ольга",
            lastname = "Иванова",
            phoneNumber = "89506116615"
        ),
        Contact(
            id = 4,
            portrait = Contact.Image.ImageDrawable(R.drawable.portrait4),
            firstname = "Семён",
            lastname = "Пирогов",
            phoneNumber = "89506116616"
        )
    )

    fun getContacts(): List<Contact> {
        return contacts
    }

    fun getContact(id: Int): Contact? {
        return contacts.firstOrNull {
            it.id == id
        }
    }

    fun editContact(contact: Contact) {
        val idX = contacts.indexOfFirst {
            it.id == contact.id
        }
        contacts.set(idX, contact)
    }
}
