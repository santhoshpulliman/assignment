package com.santhosh.assignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PersonalInfo(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Calendar
) : Parcelable