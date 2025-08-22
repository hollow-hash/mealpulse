package com.example.mealpulse.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mealpulse.models.UserModel
import com.example.mealpulse.navigation.ROUTE_DASHBOARD
import com.example.mealpulse.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel:ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun register(
        fullname:String,
        email: String,
        password: String,
        phonenumber:String,
        nationality:String,
        companyname:String,
        navController: NavController,
        context: Context
    ) {
        if (fullname.isBlank() || email.isBlank() || password.isBlank() || phonenumber.isBlank() || nationality.isBlank() || companyname.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
//            to prevent the app crushing
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: ""
                val user = UserModel(fullname = fullname, email = email, userId = userId)

                saveUserToDatabase(user, navController, context)

            } else {
                Toast.makeText(
                    context, task.exception?.message ?: "login failed", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    private fun saveUserToDatabase(
        user: UserModel,
        navController: NavController,
        context: Context
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("User/${user.userId}")
        dbRef.setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    context, "Account created successfully",
                    Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN) { popUpTo(0) }
            } else {
                Toast.makeText(
                    context,
                    task.exception?.message ?: "Failed to save user",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    fun login(username:String,
                 email:String,
                 password: String,
                 navController: NavController,
                 context: Context) {
        if (username.isBlank() || email.isBlank() || password.isBlank() ) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "You have logged in successfully",
                    Toast.LENGTH_LONG).show()
                navController.navigate(
                    ROUTE_DASHBOARD) { popUpTo(0) }

            } else {
                Toast.makeText(
                    context, task.exception?.message ?: "Account failed to be created",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}



