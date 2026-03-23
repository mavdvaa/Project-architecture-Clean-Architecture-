package com.example.cleanarchitecture

import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FactsScreen(viewModel: FactsViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            // Важно: импорт items(viewModel.facts)
            items(viewModel.facts) { fact ->
                Text(text = fact.text, modifier = Modifier.padding(vertical = 8.dp))
                Divider()
            }
        }

        OutlinedTextField(
            value = viewModel.countText,
            onValueChange = { viewModel.countText = it },
            label = { Text("Count") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { viewModel.loadFacts() },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text(if (viewModel.isLoading) "LOADING..." else "RELOAD")
        }
    }
}

