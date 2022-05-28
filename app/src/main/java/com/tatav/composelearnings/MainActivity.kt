package com.tatav.composelearnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    private fun Greetings(names: List<String> = List(1000) { "$it" }) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

    @Suppress("OPT_IN_IS_NOT_ENABLED")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Greeting(name: String) {
        Card(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            CardContent(name)
        }
    }

    @Composable
    private fun CardContent(name: String) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }

                )
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
                    onClick = onContinueClicked
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