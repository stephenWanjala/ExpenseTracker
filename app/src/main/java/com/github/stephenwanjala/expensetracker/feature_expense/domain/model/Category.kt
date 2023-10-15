package com.github.stephenwanjala.expensetracker.feature_expense.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatIndividualSuite
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.DevicesOther
import androidx.compose.material.icons.filled.Dock
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.HouseSiding
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector

enum class Category {
    FOOD,
    RENT,
    UTILITIES,
    TRAVEL,
    OTHER,
    INCOME,
    FEE,
    ENTERTAINMENT,
    CLOTHING,
    HEALTH,
    EDUCATION,
    GIFT,
    CHARITY,
    INVESTMENT,
    TAX,
    INSURANCE,
    TRANSFER,
    BUSINESS,
    PERSONAL,
    HOBBY,
    SPORTS;

    fun imageVector(): ImageVector =
        when (this) {
            FOOD -> Icons.Default.Fastfood
            RENT -> Icons.Default.CarRental
            UTILITIES -> Icons.Default.Task
            TRAVEL -> Icons.Default.TravelExplore
            OTHER -> Icons.Default.DevicesOther
            INCOME -> Icons.Default.MonetizationOn
            FEE -> Icons.Default.School
            ENTERTAINMENT -> Icons.Default.MusicNote
            CLOTHING -> Icons.Default.Dock
            HEALTH -> Icons.Default.HouseSiding
            EDUCATION -> Icons.Default.House
            GIFT -> Icons.Default.CardGiftcard
            CHARITY -> Icons.Default.GifBox
            INVESTMENT -> Icons.Default.Money
            TAX -> Icons.Default.LocalTaxi
            INSURANCE -> Icons.Default.AirlineSeatIndividualSuite
            TRANSFER -> Icons.Default.PriceChange
            BUSINESS -> Icons.Default.Business
            PERSONAL -> Icons.Default.PersonPin
            HOBBY -> Icons.Default.HourglassBottom
            SPORTS -> Icons.Default.Sports
        }
}

