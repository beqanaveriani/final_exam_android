package ge.btu.exam.themoviedb

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            // TODO [SH] add sign up activity
            finish()
        }

        signInButton.setOnClickListener {
            doLogin()
        }
    }

    public override fun onStart(){
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun doLogin(){

        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isEmpty()){
            emailInput.error = "Please provide email"
            emailInput.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.error = "Invalid email format"
            emailInput.requestFocus()
            return
        }

        if (password.isEmpty()){
            passwordInput.error = "Please provide passowrd"
            passwordInput.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }
            }

    }

    private fun updateUI(currentUser: FirebaseUser?){
        if (currentUser != null){
            // TODO [BN] Open main activity
            finish()
        } else {
            Toast.makeText(baseContext, "Please sign in", Toast.LENGTH_SHORT).show()
        }
    }
}
