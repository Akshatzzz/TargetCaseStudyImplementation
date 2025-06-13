package com.target.targetcasestudy.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.R
import com.target.targetcasestudy.theme.primaryRed

@Composable
fun HeaderComposable(
    modifier: Modifier = Modifier, headerComposablePayload: HeaderComposablePayload
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBottomShadow(
                color = Color.Black.copy(alpha = 0.1f), shadowHeight = (1.5).dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (headerComposablePayload.showArrow) {
            Icon(
                modifier = Modifier.padding(start = 4.dp),
                painter = painterResource(R.drawable.icon_left),
                contentDescription = null,
                tint = primaryRed
            )
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = headerComposablePayload.headerValue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

fun Modifier.drawBottomShadow(
    color: Color = Color.Black, shadowHeight: Dp = 4.dp
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

data class HeaderComposablePayload(
    val headerValue: String, val showArrow: Boolean
)

@Preview
@Composable
private fun DealScreenHeaderComposable() {
    HeaderComposable(
        modifier = Modifier.background(Color.White),
        HeaderComposablePayload("List", false)
    )
}