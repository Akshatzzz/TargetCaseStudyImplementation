package com.target.targetcasestudy.domain

data class DealItem(
  val id: Int,
  val title: String,
  val aisle: String,
  val description: String,
  val imageUrl: String,
  val regularPrice: Price,
  val fulfillment: String,
  val availability: String
)

data class Price(
  val amountInCents: Int,
  val currencySymbol: String,
  val displayString: String
)




internal val prevDealItem = DealItem(
  id = 0,
  title = "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV",
  aisle = "b2",
  description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/0",
  imageUrl = "https://assets.bobblekeyboard.net/app-resources/d1de4147-6272-43e5-b215-a6e6d4bcbffe_compressed.png",
  regularPrice = Price(
    amountInCents = 22999,
    currencySymbol = "$",
    displayString = "$229.99"
  ),
  fulfillment = "Online",
  availability = "In stock"
)
