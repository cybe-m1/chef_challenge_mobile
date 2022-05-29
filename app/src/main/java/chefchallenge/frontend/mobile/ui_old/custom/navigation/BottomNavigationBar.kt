package chefchallenge.frontend.mobile.ui.custom.navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavigationItem.Homepage,
        NavigationItem.User,
        NavigationItem.Ingredients
    )

    BottomNavigation(
        contentColor = Color.Black,
        )
    {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                selectedContentColor = Color.White,
                onClick = { selectedItem = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}