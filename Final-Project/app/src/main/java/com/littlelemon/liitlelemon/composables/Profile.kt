package com.littlelemon.liitlelemon.composables

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.littlelemon.liitlelemon.R
import com.littlelemon.liitlelemon.ui.theme.PrimaryGreen
import com.littlelemon.liitlelemon.ui.theme.PrimaryYellow
import com.littlelemon.liitlelemon.composables.Onboarding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController)
{
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: "sham"
    val lastName = sharedPreferences.getString("lastName", "") ?: "lal"
    val email = sharedPreferences.getString("email", "") ?: "lal@sham.com"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item{ Header() }

        item{
            Text(
                text = "Personal Information:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item{ Spacer(modifier = Modifier.height(20.dp)) }

        item{
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "First Name")
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
//                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colorScheme.surface)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Last name")
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = MaterialTheme.colors.surface
//                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email")
                OutlinedTextField(
                    value = email,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = MaterialTheme.colors.surface
//                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                // Clear shared preferences
                                val editor = sharedPreferences.edit()
                                editor.clear()
                                editor.apply()
                                // Navigate to Onboarding screen
                                navController.navigate("Onboarding")
                            },
                            border = BorderStroke(2.dp, PrimaryGreen),
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(PrimaryYellow
//                                backgroundColor = PrimaryYellow,
//                                contentColor = PrimaryGreen
                            ),


                            ) {
                            Text(text = "Log Out", style = TextStyle(fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)

        )
    }
}

@Composable
@Preview
fun previewProfile()
{
    Profile(rememberNavController())
}