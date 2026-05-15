package com.oleg.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.oleg.todoapp.ui.navigation.AppNavigation
import com.oleg.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppTheme {
                AppNavigation()
            }
        }
    }
}
