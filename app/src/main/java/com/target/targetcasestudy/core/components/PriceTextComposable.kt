package com.target.targetcasestudy.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.theme.darkGreyText
import com.target.targetcasestudy.theme.priceTextRed

@Composable
fun PriceTextComposable(
    displayAmount: String, actualPriceText: String, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = priceTextRed, fontSize = 22.sp, fontWeight = FontWeight.Bold
                    )
                ) {
                    append(displayAmount)
                }
                withStyle(
                    style = SpanStyle(
                        color = darkGreyText, fontSize = 12.sp, fontWeight = FontWeight.Normal
                    )
                ) {
                    append(" $actualPriceText")
                }
            })
    }
}

@Preview
@Composable
private fun PriceTextPreview() {
    PriceTextComposable("$399.29", "399.29", modifier = Modifier.background(Color.White))
}