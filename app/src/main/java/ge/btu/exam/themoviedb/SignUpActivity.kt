package ge.btu.exam.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        signUpButtonFirebase.setOnClickListener {
            signUp()
        }
    }

    private fun signUp(){

        val email = emailInputFirebase.text.toString()
        val password = passwordInputFirebase.text.toString()
        val confirmPassword = passwordInputFirebaseRepeat.text.toString()

        if (email.isEmpty()){
            emailInputFirebase.error = "Please provide email"
            emailInputFirebase.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInputFirebase.error = "Invalid email format"
            emailInputFirebase.requestFocus()
            return
        }

        if (password.isEmpty()){
            passwordInputFirebase.error = "Please provide password"
            passwordInputFirebase.requestFocus()
            return
        }

        if (confirmPassword.isEmpty()){
            passwordInputFirebaseRepeat.error = "Please confirm your password"
            passwordInputFirebaseRepeat.requestFocus()
            return
        }

        if (password != confirmPassword){
            passwordInputFirebaseRepeat.error = "Repeated password doesn't match"
            passwordInputFirebaseRepeat.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}
