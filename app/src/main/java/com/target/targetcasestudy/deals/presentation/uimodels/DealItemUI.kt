package com.target.targetcasestudy.deals.presentation.uimodels

import com.target.targetcasestudy.deals.domain.DealItem

data class DealItemUI(
    val id: Int,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val fulfillment: String,
    val availability: String,
    val displayAmount: String,
    val actualPriceString: String,
    val aisleString: String
)

fun DealItem.toDealItemUI(): DealItemUI {
    return DealItemUI(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        fulfillment = fulfillment,
        availability = availability,
        displayAmount = regularPrice.displayString,
        actualPriceString = "reg. ${
            formatCentsWithSymbol(
                regularPrice.amountInCents, regularPrice.currencySymbol
            )
        }",
        aisleString = "in aisle $aisle",
    )
}

fun formatCentsWithSymbol(cents: Int, symbol: String): String {
    val amount = cents / 100.0
    return "$symbol%.2f".format(amount)
}
