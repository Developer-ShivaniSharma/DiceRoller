package com.myapp.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollApp()
            }
        }
    }
}

/**
 * The DiceWithButtonAndImage fun represent the UI component and also hold the logic for image and button
   display.
 */
@Composable
//In the DiceWithButtonAndImage function accepts a argument of modifier type and value modifier.
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
//we take a result of var type
//the Composable function can store the object or value in memory using "remember"
// we szet the mutableStateOf default value as 1. But it will change whenever the Dice rolls.
    var result by remember { mutableStateOf(1) }
    // Use the when condition to print result when dice rolls. Store the result in imageResource variable of val type.
    val imageResource: Int = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
// As we see the disc and the button is on vertical sequence so for that we use column.
// in column we use modifier and horizontalAlignment. To  ensure that the children in column are centred on Device screen with respect to width.
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//As we need image before button so we add image first then button.
// in the Image fun we use painter argument and assign it painterResource that accepts imageResource as id.
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )
// For some space between button and dice we add spacer fun with modifier parameter.
// For vertical space between button and Dice we set the "height" of 16 dp.
        Spacer(modifier = Modifier.height(16.dp))
//button function works when the user click it. It rolls the dice.
 //Add the Text function which takes the String resource.
//in the Button function, parameter OnClick we pass a fun using lambda expression.
// In lambda expression we use the random() method on the range between 1 to 6.
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
  }

@Preview
@Composable
//in the DieRollApp fun we call the DiceWithButtonImage which has modifier.
// Chain the modifier with "fillMaxSize()" so that layout fills the entire screen.
//wrapContentSize() method wrap the content and argument Alignment.Centre represent that the components centre both vertically and horizontally.

fun DiceRollApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
