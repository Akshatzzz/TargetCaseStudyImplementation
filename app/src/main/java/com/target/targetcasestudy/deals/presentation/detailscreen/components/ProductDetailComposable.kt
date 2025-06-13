package com.target.targetcasestudy.deals.presentation.detailscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI
import com.target.targetcasestudy.theme.black
import com.target.targetcasestudy.theme.darkGreyText
import com.target.targetcasestudy.theme.greyMediumText
import com.target.targetcasestudy.theme.lightGreyText
import com.target.targetcasestudy.theme.priceTextRed

@Composable
fun ProductDetailComposable(
    modifier: Modifier = Modifier,
    dealItemUI: DealItemUI
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
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
            modifier = Modifier.padding(vertical = 2.dp)
        )

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = dealItemUI.displayAmount,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = priceTextRed,
            )

            Text(
                text = dealItemUI.actualPriceString,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = darkGreyText,
            )
        }

        Text(
            text = dealItemUI.fulfillment,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = lightGreyText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "Product details",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 18.sp,
            color = black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if(!dealItemUI.description.isNullOrBlank()){
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