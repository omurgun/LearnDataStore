package com.example.tryapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataStoreManager : DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataStoreManager = DataStoreManager(this)

        println("1")
        lifecycleScope.launch {
            dataStoreManager.addUser(User(1,"ömür",22,false))
            println("2")
        }

        println("3")

        lifecycleScope.launch {
            println("4")
            dataStoreManager.getUser.collect {
                if (it.firstName != "Not Found")
                {
                    Toast.makeText(this@MainActivity, "Username is ${it.firstName}", Toast.LENGTH_SHORT).show()
                    println(it.firstName)
                }
                println("5")

            }
            println("6")
        }

        println("7")





    }
}