package co.com.challenge.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class SplashActivity: AppCompatActivity() {

    private  val sleep = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            MainActivity.startActivity(this)
            finish()
        }, sleep)
    }
}