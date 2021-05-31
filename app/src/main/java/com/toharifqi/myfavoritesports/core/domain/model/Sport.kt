package com.toharifqi.myfavoritesports.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sport(
    var sportId: String,
    var format: String,
    var name: String,
    var thumb: String,
    var thumbGreen: String,
    var description: String,
    var isFavorite: Boolean
) : Parcelable
