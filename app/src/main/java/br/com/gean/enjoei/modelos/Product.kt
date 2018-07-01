package br.com.gean.enjoei.modelos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        val id: Int,
        val discount_percentage: Int,
        val photos: List<Photo>,
        val title: String,
        val price: Double,
        val original_price: Double,
        val size: String,
        val likes_count: Int,
        val maximum_installment: Int,
        val published_comments_count: Int,
        val content: String,
        val user: User
) : Parcelable