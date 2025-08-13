package com.example.mealpulse.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel:ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun login(email: String, password: String, navController: NavController, context: Context) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Login successful",
                    Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(
                    context, task.exception?.message ?: "Login failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}