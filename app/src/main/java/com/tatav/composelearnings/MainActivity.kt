package com.tatav.composelearnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call composable function only in setContent
        setContent {
            //calling composable function
            MessageCard(Message("Brian Tracy","Eat That Frog! Action Workbook: 21 Great Ways to Stop Procrastinating and Get More Done in Less Time","https://images-na.ssl-images-amazon.com/images/I/51ZsCYE0pEL._SX355_BO1,204,203,200_.jpg","https://images.gr-assets.com/authors/1258058862p8/22033.jpg"))
        }
    }
}

//sample data class
data class Message(val Author: String,val book:String,val BookImage:String,val AuthorImage:String)

//composable function
@Composable
fun MessageCard(message: Message) {
    //Row: To display elements in horizontal layout
    Row(modifier = Modifier.padding(all = 8.dp)) {
        GlideImage(
            imageModel = message.BookImage,
                    modifier = Modifier
                        // Set image size to 40 dp
                        .width(100.dp)
                        .height(150.dp)
                // Clip image to be shaped as a circle
                //.clip(CircleShape)
        )

    // Add a horizontal space between the image and the column
    Spacer(modifier = Modifier.width(8.dp))

    //Column: Used to arrange elements vertically
    Column {
        Text(text = message.book)
        Text(text ="by ")
        Row(modifier = Modifier.padding(all = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                imageModel = message.AuthorImage,
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = message.Author)
        }


    }}
}

//calling composable function for preview in only android studio
@Preview
@Composable
fun PreviewMessageCard() {
   // MessageCard(Message("Robin Sharma","The Monk Who Sold His Ferrari"))
}