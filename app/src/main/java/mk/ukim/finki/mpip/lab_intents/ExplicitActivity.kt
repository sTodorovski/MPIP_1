package mk.ukim.lab_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_intents.R
import mk.ukim.lab_intents.viewmodels.LabIntentsViewModel

class ExplicitActivity : AppCompatActivity() {

    private lateinit var VoRedButton: Button
    private lateinit var OtkaziButton: Button
    private lateinit var VnesTextView: EditText
    private lateinit var ViewModel: LabIntentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        VnesTextView = findViewById(R.id.VnesTextView)
        OtkaziButton = findViewById(R.id.OtkaziButton)
        VoRedButton = findViewById(R.id.VoRedButton)
        ViewModel = ViewModelProvider(this)[LabIntentsViewModel::class.java]
        var bundle: Bundle? = intent.extras

        ViewModel.getRezultat().observe(this) {
            VnesTextView.setText(ViewModel.getRezultatValue())
        }

        bundle?.getString("rezultat")?.let { intent -> ViewModel.setRezultat(intent) }

        VoRedButton.setOnClickListener {
            Intent().let { intent ->
                intent.putExtra("rezultat", VnesTextView.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        OtkaziButton.setOnClickListener {
            Intent().let {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}