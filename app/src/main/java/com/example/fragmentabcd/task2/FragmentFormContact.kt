package com.example.fragmentabcd.task2

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentabcd.R

class FragmentFormContact : Fragment(R.layout.contact_form) {

    private lateinit var portraitImageView: ImageView
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: Button

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var imageUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    if (result.data != null) {
                        val selectedImageUri: Uri? = result.data?.data
                        if (selectedImageUri != null) {
                            imageUri = selectedImageUri.toString()
                            portraitImageView.setImageURI(selectedImageUri)
                        }
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var saveButton: Button = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_form_to_list)
        }

        portraitImageView = view.findViewById(R.id.portraitImageView)
        firstNameEditText = view.findViewById(R.id.firstNameEditText)
        lastNameEditText = view.findViewById(R.id.lastNameEditText)
        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText)
        saveButton = view.findViewById(R.id.saveButton)

        val contact = requireNotNull(arguments?.getParcelable<Contact>("contact"))

        when (contact.portrait) {
            is Contact.Image.ImageDrawable -> portraitImageView.setImageResource(contact.portrait.resId)
            is Contact.Image.ImageUri -> portraitImageView.setImageURI(Uri.parse(contact.portrait.uri))
        }

        firstNameEditText.setText(requireNotNull(contact.firstname))
        lastNameEditText.setText(requireNotNull(contact.lastname))
        phoneNumberEditText.setText(requireNotNull(contact.phoneNumber))

        saveButton.setOnClickListener {
            val firstname = firstNameEditText.text.toString()
            val lastname = lastNameEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            val imageRes = if (imageUri != null) {
                Contact.Image.ImageUri(imageUri!!)
            } else {
                contact.portrait
            }

            val newContact = contact.copy(
                portrait = imageRes,
                firstname = firstname,
                lastname = lastname,
                phoneNumber = phoneNumber
            )

            val resultBundle = Bundle().apply {
                putParcelable("contact", newContact)
            }

            parentFragmentManager.setFragmentResult("requestKey", resultBundle)

            val action = FragmentFormContactDirections.actionFormToList()
            findNavController().navigate(action)
        }

        portraitImageView.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 100
    }
}