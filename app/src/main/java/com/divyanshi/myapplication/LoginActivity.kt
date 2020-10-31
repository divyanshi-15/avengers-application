package com.divyanshi.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var etMobileNumber: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgotPassword: TextView
    lateinit var txtRegister: TextView
    val validMobileNumber = "0123456789"
    val validPassword = arrayOf("tony", "steve", "bruce", "thanos")

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        setContentView(R.layout.activity_login)
        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }
        title = "Log In"
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        txtRegister = findViewById(R.id.txtRegister)

        btnLogin.setOnClickListener {
            val MobileNumber = etMobileNumber.text.toString()
            val Password = etPassword.text.toString()
            var nameOfAvengers = "Avengers"
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            if (MobileNumber == validMobileNumber) {
                if (Password == validPassword[0]) {

                    nameOfAvengers = "Iron Man"
                    savePreferences(nameOfAvengers)
                    startActivity(intent)
                } else if (Password == validPassword[1]) {

                    nameOfAvengers = "Captain America"
                    savePreferences(nameOfAvengers)

                    startActivity(intent)
                } else if (Password == validPassword[2]) {

                    nameOfAvengers = "The Hulk"
                    savePreferences(nameOfAvengers)

                    startActivity(intent)
                } else if (Password == validPassword[3]) {

                    nameOfAvengers = "The Avengers"
                    savePreferences(nameOfAvengers)

                    startActivity(intent)
                }


            } else {
                Toast.makeText(this@LoginActivity, "Incorrect Credentials", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(title: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
    }
}