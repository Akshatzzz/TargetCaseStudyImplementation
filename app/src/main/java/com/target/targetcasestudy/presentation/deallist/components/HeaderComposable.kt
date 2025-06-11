package com.target.targetcasestudy.presentation.deallist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderComposable(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBottomShadow(
                color = Color.Black.copy(alpha = 0.1f),
                shadowHeight = (1.5).dp
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "List",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

fun Modifier.drawBottomShadow(
    color: Color = Color.Black,
    shadowHeight: Dp = 4.dp
): Modifier = drawBehind {
    val shadowHeightPx = shadowHeight.toPx()
    val paint = Paint().apply {
        this.color = color
    }

    drawIntoCanvas { canvas ->
        canvas.drawRect(
            left = 0f,
            top = size.height - shadowHeightPx,
            right = size.width,
            bottom = size.height,
            paint
        )
    }
}