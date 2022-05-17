package chefchallenge.frontend.mobile.ui.custom.elements

import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import chefchallenge.frontend.mobile.R

@Composable
fun Home() {
    Icon(
        painterResource(id = R.drawable.ic_home_foreground) ,
        contentDescription = "Home_Icon",
        tint = White
    )
}

