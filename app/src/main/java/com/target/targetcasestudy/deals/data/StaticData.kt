package com.target.targetcasestudy.deals.data

import com.target.targetcasestudy.deals.domain.DealItem
import com.target.targetcasestudy.deals.domain.Price

object StaticData {
  val deals: List<DealItem> = listOf(
    DealItem(
      id = 0,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 1",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 1",
      aisle = "A1",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(1099, "$", "$10.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    DealItem(
      id = 1,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 2",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 2",
      aisle = "A2",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(2099, "$", "$20.99"),
      fulfillment = "In store",
      availability = "Out of stock"
    ),
    DealItem(
      id = 2,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 3",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 3",
      aisle = "A3",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(1599, "$", "$15.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    DealItem(
      id = 3,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 4",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 4",
      aisle = "A4",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(1799, "$", "$17.99"),
      fulfillment = "Online",
      availability = "Limited stock"
    ),
    DealItem(
      id = 4,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 5",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 5",
      aisle = "A5",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(899, "$", "$8.99"),
      fulfillment = "In store",
      availability = "In stock"
    ),
    DealItem(
      id = 5,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 6",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 6",
      aisle = "A6",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(2499, "$", "$24.99"),
      fulfillment = "Online",
      availability = "Out of stock"
    ),
    DealItem(
      id = 6,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 7",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 7",
      aisle = "A7",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(2999, "$", "$29.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    DealItem(
      id = 7,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 8",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 8",
      aisle = "A8",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(3499, "$", "$34.99"),
      fulfillment = "In store",
      availability = "Limited stock"
    ),
    DealItem(
      id = 8,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 9",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 9",
      aisle = "A9",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
      regularPrice = Price(4599, "$", "$45.99"),
      fulfillment = "Online",
      availability = "In stock"
    ),
    DealItem(
      id = 9,
      title = "Women's Long Sleeve Denim Jacket - Universal Thread™ 10",
      description = "This is Women's Long Sleeve Denim Jacket - Universal Thread™ number 10",
      aisle = "A10",
      imageUrl = "https://appstorage.target.com/app-data/native-tha-images/2.jpg",
      regularPrice = Price(5599, "$", "$55.99"),
      fulfillment = "In store",
      availability = "Out of stock"
    )
  )
}
