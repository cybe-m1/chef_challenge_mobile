package chefchallenge.frontend.mobile.presentation.Authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import chefchallenge.frontend.mobile.presentation.Toast
import chefchallenge.frontend.mobile.util.Response
import chefchallenge.frontend.mobile.util.Screens

@Composable
fun SignupScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember {
                mutableStateOf("")
            }
            val usernameState = remember {
                mutableStateOf("")
            }
            val passwordState = remember {
                mutableStateOf("")
            }

            Text(
                text = "Sign Up",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )


            OutlinedTextField(value = usernameState.value, onValueChange = {
                usernameState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter Your Username")
                }
            )


            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter Your Email")
                }
            )


            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter Your Password")
                },
                visualTransformation = PasswordVisualTransformation()
            )


            Button(onClick = {
                authenticationViewModel.signUp(emailState.value, passwordState.value, usernameState.value)
            },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text="Sign Up")
                when(val response = authenticationViewModel.signInState.value) {
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
                            Toast(message = "Sign Up failed")
                        }
                    }
                    is Response.Error -> {
                        Toast(response.message)
                    }
                }
            }
            Text(text = "Already have an account ? Log In ", color = Color.Blue, modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate(Screens.LoginScreen.route) {
                        launchSingleTop = true
                    }
                })
        }
    }
}