package mk.ukim.lab_intents

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.lab_intents.R

class ImplicitActivity : AppCompatActivity() {
    private lateinit var ImplicitTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        ImplicitTextView = findViewById(R.id.ImplicitTextView)
    }
}