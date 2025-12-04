package com.example.myapp006jetpackcomposeminitodo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp


data class TodoItem(
    val title: String,
    val isDone: Boolean = false
)
@Composable
fun ToDoScreen(modifier: Modifier = Modifier) {
    /*Text("TODO SCREEN",
        modifier = modifier)*/

    // Stav pro textové pole
    var text by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<TodoItem>() }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Nový úkol") },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        tasks.add(TodoItem(title = text))
                        text = ""  // vymazat pole
                    }
                }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // ----- Seznam úkolů -----
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val index = tasks.indexOf(task)
                            tasks[index] = task.copy(isDone = !task.isDone)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = task.title,
                            textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
                        )

                        TextButton(onClick = {
                            tasks.remove(task)
                        }) {
                            Text("Smazat")
                        }
                    }
                }
            }
        }
    }
}