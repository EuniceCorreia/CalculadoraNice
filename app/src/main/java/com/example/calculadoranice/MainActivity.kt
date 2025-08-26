package com.example.calculadoranice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadoranice.ui.theme.CalculadoraNiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

                }
            }
        }

@Preview
@Composable
fun calculadoraApp () {

    Row (

        Button(onClick = {}) {
            Text("9")
    )


    Column(
        modifier = Modifier
            .background(Color.Red)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {}) {
                Text("7")
            }

            Button(onClick = {}) {
                Text("8")
            }

            Button(onClick = {}) {
                Text("9")
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {}) {
                Text("4")
            }

            Button(onClick = {}) {
                Text("5")
            }

            Button(onClick = {}) {
                Text("6")
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {}) {
                Text("1")
            }

            Button(onClick = {}) {
                Text("2")
            }

            Button(onClick = {}) {
                Text("3")
            }
        }
    }
}