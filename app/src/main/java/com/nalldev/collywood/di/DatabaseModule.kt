package com.nalldev.collywood.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.ealva.welite.db.Database
import com.ealva.welite.db.OpenParams
import com.nalldev.collywood.data.db.UserTable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { createDb(androidContext()) }
    single { createPreferences(androidContext()) }
}

private fun createDb(context : Context) : Database {
    return Database(
        context = context,
        fileName = "MyDB",
        tables = setOf(UserTable),
        version = 1,
        openParams = OpenParams(
            enableWriteAheadLogging = true,
            enableForeignKeyConstraints = true,
        )
    )
}

private fun createPreferences(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}