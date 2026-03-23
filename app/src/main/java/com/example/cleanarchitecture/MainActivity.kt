package com.example.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Public
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация
        val api = Retrofit.Builder()
            .baseUrl("https://catfact.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CatApi::class.java)

        val repo = CatRepositoryImpl(api)
        val useCase = GetFactsUseCase(repo)
        val viewModel = FactsViewModel(useCase)

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.List, contentDescription = null) },
                            label = { Text("Facts") },
                            selected = true, // Для простоты оставим true или добавьте логику состояний
                            onClick = { navController.navigate("facts") }
                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Public, contentDescription = null) },
                            label = { Text("Web") },
                            selected = false,
                            onClick = { navController.navigate("web") }
                        )
                    }
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = "facts",
                    modifier = Modifier.padding(padding)
                ) {
                    composable("facts") { FactsScreen(viewModel) }
                    composable("web") { WebScreen() }
                }
            }
        }
    }
}
