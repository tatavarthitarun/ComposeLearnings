package com.tatav.composelearnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatav.composelearnings.ui.theme.ComposeLearnings


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call composable function only in setContent
        setContent {
            ComposeLearnings {
              MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {

        //Hoisting a state to be able to used by one or more composable functions
        var shouldShowOnboarding by remember { mutableStateOf(true) }

        if (shouldShowOnboarding) {
            //sending the state value up from the composable function
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }

    @Composable
    private fun Greetings(names: List<String> = listOf("World", "Compose")) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }

    @Composable
    private fun Greeting(name: String) {

        //remember: to avoid change during recomposing
        val expanded = remember { mutableStateOf(false) }

        val extraPadding = if (expanded.value) 48.dp else 0.dp

        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp),) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello, ")
                    Text(text = name)
                }
                Button(
                    shape = MaterialTheme.shapes.medium,
                    onClick = { expanded.value = !expanded.value },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Blue)
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }

        }
    }

    //State Hoisting

    @Composable
    fun OnboardingScreen(onContinueClicked: () -> Unit) {

        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to the Basics Codelab!")
                Button(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick =  onContinueClicked
                ) {
                    Text("Continue")
                }
            }
        }
    }

    @Preview(showBackground = true, widthDp = 320, heightDp = 320)
    @Composable
    fun OnboardingPreview() {
        ComposeLearnings {
            MyApp()
        }
    }

}