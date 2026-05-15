package com.oleg.todoapp

import android.app.Application
import com.oleg.todoapp.di.AppContainer
import com.oleg.todoapp.di.DefaultAppContainer

class TodoApplication : Application() {
    val appContainer: AppContainer by lazy { DefaultAppContainer() }
}
