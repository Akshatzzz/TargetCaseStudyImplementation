package com.target.targetcasestudy.deals.presentation.detailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.target.targetcasestudy.core.components.PriceTextComposable
import com.target.targetcasestudy.deals.domain.prevDealItem
import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI
import com.target.targetcasestudy.deals.presentation.uimodels.toDealItemUI
import com.target.targetcasestudy.theme.black
import com.target.targetcasestudy.theme.greyMediumText
import com.target.targetcasestudy.theme.lightGreyStrip
import com.target.targetcasestudy.theme.lightGreyText

@Composable
fun ProductDetailComposable(
    modifier: Modifier = Modifier,
    dealItemUI: DealItemUI
) {
    Column {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            GlideImage(
                imageModel = { dealItemUI.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .size(328.dp)
                    .clip(RoundedCornerShape(6.dp))
            )

            Text(
                text = dealItemUI.title,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            PriceTextComposable(dealItemUI.displayAmount, dealItemUI.actualPriceString)

            Text(
                text = dealItemUI.fulfillment,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = lightGreyText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        GreyStripWithBorders()
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Product details",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 18.sp,
                color = black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            if (!dealItemUI.description.isNullOrBlank()) {
                Text(
                    text = dealItemUI.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    color = greyMediumText
                )
            }
        }
    }
}

@Composable
fun GreyStripWithBorders() {
    Column {
        Divider(color = Color.LightGray, thickness = 0.5.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(lightGreyStrip)
        )

        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}


@Preview
@Composable
private fun DetailComposable() {
    ProductDetailComposable(
        dealItemUI = prevDealItem.toDealItemUI(), modifier = Modifier.background(
            Color.White
        )
    )
}