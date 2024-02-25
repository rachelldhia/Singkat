package com.example.singkat.login


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.singkat.MainActivity
import com.example.singkat.R
import com.example.singkat.SharedPreference
import com.example.singkat.api.ApiService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {


    lateinit var sph : SharedPreferences



    private lateinit var usernameLayout: TextInputLayout
    private lateinit var idLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var idEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var button: AppCompatButton


    private fun isInputValid(): Boolean {

        var isValid = true

        if (usernameEditText.text.isNullOrEmpty()) {
            usernameLayout.error = "Username is required"
            isValid = false
        } else {
            usernameLayout.error = null
        }

        if (idEditText.text.isNullOrEmpty()) {
            idLayout.error = "id is required"
            isValid = false
        } else {
            idLayout.error = null
        }

        if (passwordEditText.text.isNullOrEmpty()) {
            passwordLayout.error = "Password is required"
            isValid = false
        } else {
            passwordLayout.error = null
        }

        return isValid


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        sph = SharedPreference(this)


        usernameLayout = findViewById(R.id.usernameLayout)
        idLayout = findViewById(R.id.idLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        usernameEditText = findViewById(R.id.usernameEditText)
        idEditText = findViewById(R.id.idEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        button = findViewById(R.id.button)

        button.setOnClickListener {

            if (isInputValid()) {
                val username = usernameEditText.text.toString()
                val idUser = idEditText.text.toString()
                val password = passwordEditText.text.toString()

                login(username, idUser, password)
            }
        }

    }

    private fun login(username: String, idUser: String, password: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("") //api nya
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.login(username, idUser, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true) {
                        // Login berhasil
                        Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT)
                            .show()


                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Login gagal
                        Toast.makeText(
                            this@LoginActivity,
                            "Login gagal. ${loginResponse?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Respon tidak berhasil
                    Toast.makeText(
                        this@LoginActivity,
                        "Gagal menghubungi server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Kesalahan jaringan atau kesalahan lainnya
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })



    }
}



