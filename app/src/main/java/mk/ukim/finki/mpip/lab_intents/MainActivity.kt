package mk.ukim.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.lab_intents.R
import mk.ukim.lab_intents.viewmodels.LabIntentsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var EActivityButton: Button
    private lateinit var IActivityButton: Button
    private lateinit var ImageButton: Button
    private lateinit var MainTextView: TextView
    private lateinit var ViewModel: LabIntentsViewModel

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val rezultat = data?.getStringExtra("rezultat")

            if(rezultat != null) {
                ViewModel.setRezultat(rezultat)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EActivityButton = findViewById(R.id.EActivityButton)
        IActivityButton = findViewById(R.id.IActivityButton)
        ImageButton = findViewById(R.id.ImageButton)
        MainTextView = findViewById(R.id.textView)
        ViewModel = ViewModelProvider(this)[LabIntentsViewModel::class.java]

        ViewModel.getRezultat().observe(this) {
            MainTextView.setText(ViewModel.getRezultatValue())
        }

        EActivityButton.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let { intent ->
                intent.putExtra("rezultat", ViewModel.getRezultatValue())
                resultLauncher.launch(intent)
            }
        }

        IActivityButton.setOnClickListener {
            Intent().apply {
                action = "mk.ukim.finki.lab_intents.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { intent ->
                startActivity(intent)
            }
        }

        ImageButton.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }.let { intent ->
                resultLauncher.launch(intent)
            }
        }
    }
}