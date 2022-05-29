package chefchallenge.frontend.mobile.ui.custom.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import chefchallenge.frontend.mobile.ui.NavigationKeys

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Homepage: NavigationItem(NavigationKeys.Route.HOMEPAGE, Icons.Filled.Menu, "Homepage")
    object User: NavigationItem(NavigationKeys.Route.USER, Icons.Filled.Person, "User")
    object Ingredients: NavigationItem(NavigationKeys.Route.INGREDIENTS, Icons.Filled.Notifications, "Ingredients")
}