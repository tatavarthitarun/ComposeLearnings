package com.tatav.savedstateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call composable function only in setContent
        setContent {
            //calling composable function
            MessageCard(Message("Tarun","Dad i hate you"))

        }
    }
}

//sample data class
data class Message(val Author: String,val body:String)

//composable function
@Composable
fun MessageCard(message: Message) {
    Text(text = message.Author)
    Text(text = message.body)
}
//calling composable function for preview
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("TataV","GrandMa,Next Door"))
}
