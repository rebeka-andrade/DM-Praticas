package com.example.weatherapp


import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfir by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo/a!",
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.size(24.dp))
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Digite seu nome") },
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { nome = it }
        )
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { email = it }
        )
        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = passwordConfir,
            label = { Text(text = "Confirme sua senha") },
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { passwordConfir = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Registro OK!", Toast.LENGTH_LONG).show()
                    activity.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                enabled = (nome.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfir.isNotEmpty()) && password == passwordConfir
            ) {
                Text("Registrar")
            }
            Spacer(modifier = modifier.size(24.dp))
            Button(
                onClick = { nome = ""; email = ""; password = ""; passwordConfir = ""; }
            ) {
                Text("Limpar")
            }
            Spacer(modifier = modifier.size(24.dp))
            Button(
                onClick = {
                    activity.finish() }
            ) {
                Text("Sair")
            }
        }
    }
}