package com.example.jetpackapp1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapp1.ui.theme.JetpackApp1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackApp1Theme {

                val context = LocalContext.current
                var name by remember {
                    mutableStateOf("")
                }
                var names by remember {
                    mutableStateOf(listOf<String>())
                }
                val keyboardController = LocalSoftwareKeyboardController.current


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.2f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        OutlinedTextField(
                            value = name,
                            textStyle = TextStyle(color = Color.Black),
                            onValueChange = { name = it },
                            label = { Text("enter your name") },
                            singleLine = true,
//                            keyboardController = keyboardController,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    names += name
                                    name = ""
                                    keyboardController?.hide()
                                }
                            ),
                        )


                        Column(
//                            modifier = Modifier.fillMaxWidth()
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(onClick = {

                                if (name == "") {
                                    Toast.makeText(context, "First enter the name!", Toast.LENGTH_SHORT).show()
                                } else {
                                    names = names + name
                                    name = ""
                                }
                            },
                                colors = ButtonDefaults.buttonColors(
//                                    backgroundColor = Color.Black
                                    containerColor = Color.Black,
                                    contentColor = Color.Red
                                )
                            ) {
                                Text(text = "Add")
                            }
                            
                            Button(onClick = {
                                var isNameExist = false
                                for (item in names){
                                    if (item==name){
                                        isNameExist = true
                                        break
                                    }
                                }

                                if (!name.isBlank()){
                                    if (isNameExist){
                                        names = names.filter { it != name }.toMutableList()
                                    }else{
                                        Toast.makeText(context, "This element is not exist in the list!", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Toast.makeText(context, "First enter the name", Toast.LENGTH_SHORT).show()
                                }
                            },
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = Color.Black
                                ),


                            ) {
                                Text(text = "remove",
                                    maxLines = 1,
                                    overflow = TextOverflow.Visible,
                                    modifier = Modifier.padding(1.dp),
                                    fontSize = 12.sp
                                    )
                            }

                        }

                    }

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .background(androidx.compose.ui.graphics.Color.Yellow)
                                .padding(6.dp),

                            ) {
                            items(names) { currentName ->
                                Text(
                                    text = currentName,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .background(Color.White)
                                        .border(1.dp, Color.Black)
                                        .wrapContentSize(Alignment.Center),
                                    fontSize = 20.sp,
                                    style = TextStyle(color = Color.Black)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }


                }
            }
        }
    }
}
