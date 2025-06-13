package com.target.targetcasestudy.deals.presentation.detailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.theme.primaryRed
import com.target.targetcasestudy.theme.white

@Composable
fun AddToCardButtonComposable(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                clip = true
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primaryRed, contentColor = white
            ),
            onClick = { onClick() }) {
            Text(
                text = "Add to cart", fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun AddToCartComposablePrev() {
    AddToCardButtonComposable() {}
}