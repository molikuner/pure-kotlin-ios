package com.molikuner.kn.ios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App(modifier: Modifier = Modifier) {
    Text(
        text = "Hello from Compose JB!",
        modifier = modifier
            .background(Color.Green)
            .wrapContentSize()
    )
}
