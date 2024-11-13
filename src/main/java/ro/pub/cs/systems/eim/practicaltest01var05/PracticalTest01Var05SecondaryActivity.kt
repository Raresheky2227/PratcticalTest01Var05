package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.pub.cs.systems.eim.practicaltest01var05.ui.theme.PracticalTest01Var05Theme

class PracticalTest01Var05SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_secondary)

        // Get the TextView from the previous activity's data
        val textFromMainActivity = intent.getStringExtra("TEXT_FIELD_DATA")
        val textField: TextView = findViewById(R.id.textField)
        textField.text = textFromMainActivity

        // Find the Verify and Cancel buttons by ID
        val verifyButton: Button = findViewById(R.id.verifyButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        verifyButton.setOnClickListener {
            // Return the result to MainActivity
            val intent = Intent()
            intent.putExtra("RESULT_TEXT", "Verify clicked")
            setResult(RESULT_OK, intent)  // RESULT_OK signifies successful result
            finish()  // Close the activity and return to MainActivity
        }

        cancelButton.setOnClickListener {
            // Return the result to MainActivity
            val intent = Intent()
            intent.putExtra("RESULT_TEXT", "Cancel clicked")
            setResult(RESULT_OK, intent)  // RESULT_OK signifies successful result
            finish()  // Close the activity and return to MainActivity
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PracticalTest01Var05Theme {
        Greeting2("Android")
    }
}