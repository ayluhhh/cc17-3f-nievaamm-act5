package com.example.artspace

import android.os.Bundle
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var imgsw: ImageSwitcher
    private lateinit var prev: Button
    private lateinit var nxt: Button
    private lateinit var descriptionTextView: TextView

    private val imageIds = arrayOf(
        R.drawable.dp130155,
        R.drawable.johannes_vermeer_1632_1675___the_girl_with_the_pearl_earring_1665_e1435072137333,
        R.drawable.salvador_dali_persistence_of_memory,
        R.drawable.van_gogh___starry_night___google_art_project,
        R.drawable.monet_impression_sunrise1
    )

    private val desc = arrayOf(
        "The Great Wave Off Kanagawa",
        "The Girl with the Pearl Earring",
        "The Persistence of Memory",
        "Starry Night",
        "Impression, Sunrise",
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgsw = findViewById(R.id.frameLayout)
        prev = findViewById(R.id.prev)
        nxt = findViewById(R.id.nxt)
        descriptionTextView = findViewById(R.id.textView)

        imgsw.setFactory {
            val imageView = ImageView(applicationContext)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView
        }

        updateContent()

        prev.setOnClickListener {
            currentIndex = (currentIndex - 1 + imageIds.size) % imageIds.size
            updateContent()
        }

        nxt.setOnClickListener {
            currentIndex = (currentIndex + 1) % imageIds.size
            updateContent()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateContent() {
        imgsw.setImageResource(imageIds[currentIndex])
        descriptionTextView.text = desc[currentIndex]
    }
}