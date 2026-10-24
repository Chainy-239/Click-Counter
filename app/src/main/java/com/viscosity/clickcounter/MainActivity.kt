package com.viscosity.clickcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viscosity.clickcounter.ui.theme.ClickCounterTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.SavedStateViewModelFactory
import android.app.Application

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val myViewModel: ClickCounterViewModel = viewModel(
                        factory = SavedStateViewModelFactory(
                            application = applicationContext.applicationContext as Application,
                            owner = this
                        )
                    )

                    // Yahan se saara saaman ViewModel ka lekar screen me jaa raha hai
                    ClickCounterScreen(
                        count = myViewModel.count,
                        onIncrement = { myViewModel.incrementCount() },
                        onDecrement = { myViewModel.decrementCount() },
                        onReset = { myViewModel.resetCount() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ClickCounterScreen(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App ka title
        Text(
            text = "Click Count", fontSize = 32.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Counter Value (Ab ye seedhe count variable se chal raha hai)
        Text(
            text = "$count", fontSize = 96.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Plus aur Minus Button ek line main (Row)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Minus Button (Red Circle)
            Button(
                onClick = onDecrement, // 👈 Puraana viewModel.decrementCount() hata diya
                enabled = count > 0,   // 👈 Puraana viewModel.count hata diya
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF0F00), disabledContainerColor = Color.LightGray
                ),
                shape = CircleShape, modifier = Modifier.size(72.dp),
            ) {
                Text(
                    text = "-", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White
                )
            }

            // Plus Button (Green Circle)
            Button(
                onClick = onIncrement, // 👈 Puraana viewModel.incrementCount() hata diya
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00FF0B)
                ), shape = CircleShape, modifier = Modifier.size(72.dp)
            ) {
                Text(
                    text = "+", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Reset Button (Blue Rectangle)
        Button(
            onClick = onReset, // 👈 Puraana viewModel.resetCount() hata diya
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00B0FF)
            ), shape = RoundedCornerShape(16.dp), modifier = Modifier.width(100.dp)
        ) {
            Text(
                text = "Reset", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClickCounterScreenPreview() {
    ClickCounterTheme {
        ClickCounterScreen(
            count = 5,
            onIncrement = {},
            onDecrement = {},
            onReset = {}
        )
    }
}
