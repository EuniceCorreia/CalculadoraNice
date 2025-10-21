import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraSimples()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraSimples() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate() {
        try {
            val res = eval(input)
            result = res.toString()
        } catch (e: Exception) {
            result = "Erro"
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Calculadora Simples") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = input,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(text = result, fontSize = 24.sp, color = Color.Gray)
            }

            val buttons = listOf(
                listOf("7", "8", "9", "÷"),
                listOf("4", "5", "6", "×"),
                listOf("1", "2", "3", "-"),
                listOf("C", "0", ".", "+"),
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                buttons.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        row.forEach { label ->
                            Button(
                                onClick = {
                                    when (label) {
                                        "C" -> { input = ""; result = "" }
                                        "+" , "-", "×", "÷" -> {
                                            if (input.isNotEmpty() && !input.last().isOperator()) input += label
                                        }
                                        "=" -> calculate()
                                        else -> input += label
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (label.isOperator()) Color(0xFFFF9800) else Color(0xFF6200EE)
                                )
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Button(
                    onClick = { calculate() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("=", fontSize = 28.sp, color = Color.White)
                }
            }
        }
    }
}

private fun String.isOperator(): Boolean {
    TODO("Not yet implemented")
}

fun Char.isOperator() = this == '+' || this == '-' || this == '×' || this == '÷'

fun eval(expr: String): Double {
    if (expr.isEmpty()) return 0.0

    val cleanExpr = expr.replace("×", "*").replace("÷", "/")
    val regex = Regex("([-+]?[0-9]*\\.?[0-9]+)([+\\-*/])([-+]?[0-9]*\\.?[0-9]+)")
    val match = regex.find(cleanExpr)

    return if (match != null) {
        val (a, op, b) = match.destructured
        val num1 = a.toDouble()
        val num2 = b.toDouble()

        when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> Double.NaN
        }
    } else {
        cleanExpr.toDoubleOrNull() ?: Double.NaN
    }
}
