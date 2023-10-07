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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: "Elie"
    val lastName = sharedPreferences.getString("lastName", "") ?: "Hanna"
    val email = sharedPreferences.getString("email", "") ?: "elie@hanna.com"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item{
            Row(
                modifier = Modifier.fillMaxWidth().size(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            navController.navigate(com.littlelemon.liitlelemon.Home.route)
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }
        }

        item{
            Spacer(modifier = Modifier.size(10.dp))
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
                Text(text = "First name")
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Last name")
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email")
                OutlinedTextField(
                    value = email,
                    onValueChange = { /* No-op as it's static text */ },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
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

                            modifier = Modifier.fillMaxWidth() .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PrimaryYellow,
                                contentColor = PrimaryGreen
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



@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}