package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

class MainActivity : ComponentActivity() {

    private lateinit var uneditableTextField: TextView
    private var buttonPressCount = 0
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_main)

        // Load button press count from SharedPreferences
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        buttonPressCount = sharedPreferences.getInt("BUTTON_PRESS_COUNT", 0)

        // Show a toast with the current count at activity start
        Toast.makeText(this, "Button presses: $buttonPressCount", Toast.LENGTH_LONG).show()

        // Find the TextView and Buttons by ID
        uneditableTextField = findViewById(R.id.uneditableTextField)
        val extraTopRightButton: Button = findViewById(R.id.extraTopRightButton)
        val topLeftButton: Button = findViewById(R.id.topLeftButton)
        val topRightButton: Button = findViewById(R.id.topRightButton)
        val centerButton: Button = findViewById(R.id.centerButton)
        val bottomLeftButton: Button = findViewById(R.id.bottomLeftButton)
        val bottomRightButton: Button = findViewById(R.id.bottomRightButton)

        // Function to handle button press and update count
        fun onButtonPressed(text: String) {
            buttonPressCount++
            uneditableTextField.text = "${uneditableTextField.text} $text\n"
            saveButtonPressCount(buttonPressCount)
        }

        // Set click listeners for each button
        topLeftButton.setOnClickListener { onButtonPressed("Top Left Button clicked") }
        topRightButton.setOnClickListener { onButtonPressed("Top Right Button clicked") }
        centerButton.setOnClickListener { onButtonPressed("Center Button clicked") }
        bottomLeftButton.setOnClickListener { onButtonPressed("Bottom Left Button clicked") }
        bottomRightButton.setOnClickListener { onButtonPressed("Bottom Right Button clicked") }


        // Set up the listener to open the secondary activity
        extraTopRightButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra("TEXT_FIELD_DATA", uneditableTextField.text.toString())
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    // Save button press count to SharedPreferences
    private fun saveButtonPressCount(count: Int) {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("BUTTON_PRESS_COUNT", count)
            apply()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val resultText = data?.getStringExtra("RESULT_TEXT") ?: "No result returned"
            uneditableTextField.append("\n$resultText")  // Append to TextView
            buttonPressCount = 0
            Toast.makeText(this, "Button presses: $buttonPressCount Second activity returned: $resultText", Toast.LENGTH_LONG).show()
            uneditableTextField.text = ""
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticalTest01Var05Theme {
        Greeting("Android")
    }
}