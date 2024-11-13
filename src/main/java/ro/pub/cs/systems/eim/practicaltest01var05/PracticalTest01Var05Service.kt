package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class PracticalTest01Var05Service : Service() {

    private val handler = Handler()
    private var layoutElements: List<String>? = null  // List of elements to broadcast about
    private var currentIndex = 0  // To keep track of which element to broadcast

    override fun onBind(intent: Intent): IBinder? {
        // Not implementing binding for this service, so we return null
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Get layout elements from the intent (for this example, a list of strings)
        layoutElements = intent.getStringArrayListExtra("LAYOUT_ELEMENTS")
        currentIndex = 0  // Reset index

        // Start broadcasting messages
        startBroadcasting()

        // Return START_STICKY to keep the service running
        return START_STICKY
    }

    private fun startBroadcasting() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (layoutElements != null && layoutElements!!.isNotEmpty()) {
                    // Broadcast message for the current layout element
                    val message = layoutElements!![currentIndex]

                    // Create the broadcast intent
                    val broadcastIntent = Intent("com.example.broadcast.DISPLAY_MESSAGE")
                    broadcastIntent.putExtra("MESSAGE", message)
                    sendBroadcast(broadcastIntent)

                    Log.d("PracticalTest01Var05Service", "Broadcasting message: $message")


                    // Update index for next element (loop through the list)
                    currentIndex = (currentIndex + 1) % layoutElements!!.size
                }

                // Repeat this task every 5 seconds
                handler.postDelayed(this, 5000)
            }
        }, 5000)
    }

    override fun onDestroy() {
        // Stop broadcasting when service is destroyed
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
