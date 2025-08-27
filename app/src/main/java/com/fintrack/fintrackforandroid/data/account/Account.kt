package com.fintrack.fintrackforandroid.data.account

import androidx.compose.ui.graphics.vector.ImageVector

data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String,
    val icon: ImageVector
)