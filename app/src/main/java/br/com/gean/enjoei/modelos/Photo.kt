package br.com.gean.enjoei.modelos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
        val public_id: String,
        val crop: String,
        val gravity: String
) : Parcelable