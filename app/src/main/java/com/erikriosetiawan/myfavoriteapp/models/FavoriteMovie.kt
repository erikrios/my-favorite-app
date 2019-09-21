package com.erikriosetiawan.myfavoriteapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMovie(
    var popularity: String? = null,
    var voteCount: String? = null,
    var posterPath: String? = null,
    var id: String? = null,
    var title: String? = null,
    var voteAverage: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null
) : Parcelable