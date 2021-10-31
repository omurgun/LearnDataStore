package com.example.tryapp


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.tryapp.Constants.Companion.CONSTANT_USER_AGE
import com.example.tryapp.Constants.Companion.CONSTANT_USER_FIRSTNAME
import com.example.tryapp.Constants.Companion.CONSTANT_USER_ID
import com.example.tryapp.Constants.Companion.CONSTANT_USER_IS_CLEVER
import com.example.tryapp.PreferenceKeys.USER_AGE
import com.example.tryapp.PreferenceKeys.USER_FIRSTNAME
import com.example.tryapp.PreferenceKeys.USER_ID
import com.example.tryapp.PreferenceKeys.USER_IS_CLEVER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tryAppDataStore")

object PreferenceKeys {
    val USER_ID = intPreferencesKey(CONSTANT_USER_ID)
    val USER_FIRSTNAME = stringPreferencesKey(CONSTANT_USER_FIRSTNAME)
    val USER_AGE = intPreferencesKey(CONSTANT_USER_AGE)
    val USER_IS_CLEVER = booleanPreferencesKey(CONSTANT_USER_IS_CLEVER)
}

class DataStoreManager(private val context : Context) {
    private val dataStore = context.dataStore

    suspend fun addUser(user : User) {
        dataStore.edit {
            it[USER_ID] = user.id
            it[USER_FIRSTNAME] = user.firstName
            it[USER_AGE] = user.age
            it[USER_IS_CLEVER] = user.isClever
        }
    }

    val getUser: Flow<User> = dataStore.data
        .map {
            User(
                id = it[USER_ID]?:0,
                firstName = it[USER_FIRSTNAME]?:"Not Found" ,
                age = it[USER_AGE]?:0,
                isClever = it[USER_IS_CLEVER]?:false,)
        }

}