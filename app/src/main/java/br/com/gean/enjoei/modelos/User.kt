package br.com.gean.enjoei.modelos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val id: Int,
        val name: String,
        val avatar: Avatar
) : Parcelable