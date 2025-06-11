package com.target.targetcasestudy.presentation.deallist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
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
import com.target.targetcasestudy.domain.prevDealItem
import com.target.targetcasestudy.presentation.uimodels.DealItemUI
import com.target.targetcasestudy.presentation.uimodels.toDealItemUI
import com.target.targetcasestudy.theme.black
import com.target.targetcasestudy.theme.darkGreyText
import com.target.targetcasestudy.theme.inStockGreenText
import com.target.targetcasestudy.theme.lightGreyText
import com.target.targetcasestudy.theme.priceTextRed
import com.target.targetcasestudy.theme.white

@Composable
fun DealItemComposable(modifier: Modifier = Modifier,dealItem: DealItemUI) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = { dealItem.imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            ),
            modifier = modifier.size(130.dp).clip(RoundedCornerShape(6.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .alignBy (LastBaseline),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = dealItem.displayAmount,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = priceTextRed,
                    modifier = Modifier.alignBy(LastBaseline).padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = dealItem.actualPriceString,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    color = darkGreyText,
                    modifier = Modifier.alignBy(LastBaseline)
                )
            }

            Text(
                text = dealItem.fulfillment,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = lightGreyText
            )

            Text(
                text = dealItem.title,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = inStockGreenText)) {
                        append(dealItem.availability)
                    }
                    withStyle(style = SpanStyle(color = lightGreyText)) {
                        append(" ${dealItem.aisleString}")
                    }
                },
                fontSize = 12.sp
            )

        }
    }
}


@Preview
@PreviewLightDark
@Composable
private fun DetailItemComposable() {
    val dealItemUi = prevDealItem.toDealItemUI()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        DealItemComposable(
            dealItem = dealItemUi,
            modifier = Modifier.background(color = white),
        )
    }
}