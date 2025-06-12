package com.target.targetcasestudy.deals.presentation.deallist.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.target.targetcasestudy.deals.domain.prevDealItem
import com.target.targetcasestudy.deals.presentation.uimodels.DealItemUI
import com.target.targetcasestudy.deals.presentation.uimodels.toDealItemUI
import com.target.targetcasestudy.theme.black
import com.target.targetcasestudy.theme.darkGreyText
import com.target.targetcasestudy.theme.inStockGreenText
import com.target.targetcasestudy.theme.lightGreyText
import com.target.targetcasestudy.theme.priceTextRed
import com.target.targetcasestudy.theme.white

@Composable
fun DealItemComposable(modifier: Modifier = Modifier, dealItem: DealItemUI) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        GlideImage(
            imageModel = { dealItem.imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            ),
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(6.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = dealItem.displayAmount,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = priceTextRed,
                )

                Text(
                    text = dealItem.actualPriceString,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = darkGreyText,
                )
            }
            
            Text(
                text = dealItem.fulfillment,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = lightGreyText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = dealItem.title,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = inStockGreenText)) {
                        append(dealItem.availability)
                    }
                    withStyle(style = SpanStyle(color = lightGreyText)) {
                        append(" ${dealItem.aisleString}")
                    }
                },
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 120)
@Preview(widthDp = 600, heightDp = 120)
@Preview(widthDp = 360, heightDp = 120, uiMode = Configuration.UI_MODE_NIGHT_YES)
@PreviewLightDark
@Composable
private fun DetailItemComposable() {
    val dealItemUi = prevDealItem.toDealItemUI()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white),
        contentAlignment = Alignment.Center
    ) {
        DealItemComposable(
            dealItem = dealItemUi,
        )
    }
}