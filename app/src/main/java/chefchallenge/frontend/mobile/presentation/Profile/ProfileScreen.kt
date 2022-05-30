package chefchallenge.frontend.mobile.presentation.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import chefchallenge.frontend.mobile.presentation.Authentication.AuthenticationViewModel
import chefchallenge.frontend.mobile.presentation.BottomNavigationItem
import chefchallenge.frontend.mobile.presentation.BottomNavigationMenu
import chefchallenge.frontend.mobile.presentation.Toast
import chefchallenge.frontend.mobile.util.Response
import chefchallenge.frontend.mobile.util.Screens

@Composable
fun ProfileScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            val emailState = remember {
//                mutableStateOf("")
//            }
//            val passwordState = remember {
//                mutableStateOf("")
//            }
//
//            Text(
//                text = "Sign In",
//                modifier = Modifier.padding(10.dp),
//                fontSize = 30.sp,
//                fontFamily = FontFamily.SansSerif
//            )
//
//            OutlinedTextField(value = emailState.value, onValueChange = {
//                emailState.value = it
//            },
//                modifier = Modifier.padding(10.dp),
//                label = {
//                    Text(text = "Enter Your Email")
//                }
//            )
//
//            OutlinedTextField(value = passwordState.value, onValueChange = {
//                passwordState.value = it
//            },
//                modifier = Modifier.padding(10.dp),
//                label = {
//                    Text(text = "Enter Your Password")
//                },
//                visualTransformation = PasswordVisualTransformation()
//            )

            Button(onClick = {
                authenticationViewModel.signOut()
            },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text="Sign Out")
                when(val response = authenticationViewModel.signOutState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Response.Success -> {
                        if (response.data) {
                            navController.navigate(Screens.HomepageScreen.route) {
                                popUpTo(Screens.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast(message = "Sign Out failed")
                        }
                    }
                    is Response.Error -> {
                        Toast(response.message)
                    }
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text="Ingredient Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.INGREDIENTS, navController = navController)
    }
}