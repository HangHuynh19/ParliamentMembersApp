package com.example.parliamentmembersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.network.ParliamentApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}