package com.wangxingxing.appwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wangxingxing.appwidget.databinding.ActivityMainBinding
import javax.security.auth.login.LoginException

const val TAG = "wxx"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date = intent?.data
        Log.i(TAG, "onCreate: $date")
        date?.let {
            val idRes = Integer.parseInt(it.schemeSpecificPart)
            Log.i(TAG, "onCreate: $idRes")
            when(idRes){
                R.id.iv_big -> {
                    Toast.makeText(this,"点击大图", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}