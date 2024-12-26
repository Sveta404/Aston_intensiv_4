package com.example.fragmentabcd.task2

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val portrait: Image,
    val firstname: String,
    val lastname: String,
    val phoneNumber: String
) : Parcelable {

    sealed interface Image : Parcelable {
        @Parcelize
        data class ImageDrawable(
            @DrawableRes
            val resId: Int
        ) : Image, Parcelable

        @Parcelize
        data class ImageUri(val uri: String) : Image, Parcelable
    }
}
