package com.tatav.composelearnings

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
            MessageCard(Message("Brian Tracy","Eat That Frog! Action Workbook: 21 Great Ways to Stop Procrastinating and Get More Done in Less Time"))

        }
    }
}

//sample data class
data class Message(val Author: String,val book:String)

//composable function
@Composable
fun MessageCard(message: Message) {
    Text(text = message.Author)
    Text(text = message.book)
}

//calling composable function for preview in only android studio
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Robin Sharma","The Monk Who Sold His Ferrari"))
}
