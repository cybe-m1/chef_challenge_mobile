package chefchallenge.frontend.mobile.presentation.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import chefchallenge.frontend.mobile.presentation.BottomNavigationItem
import chefchallenge.frontend.mobile.presentation.BottomNavigationMenu

@Composable
fun ReceipeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text="Receipe Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.RECEIPE, navController = navController)
    }
}