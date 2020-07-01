package ge.btu.exam.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        tmdb_logo.alpha = 0f;
        tmdb_logo.animate().setDuration(3500).alpha(1f).withEndAction{
            val mainPage = Intent(this, LoginActivity::class.java)
            startActivity(mainPage)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
