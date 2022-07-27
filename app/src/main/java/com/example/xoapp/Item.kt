package com.example.xoapp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize

data class Item(
    val id: Int,
    val player: Int,
    @DrawableRes val icon: Int,
) : Parcelable
