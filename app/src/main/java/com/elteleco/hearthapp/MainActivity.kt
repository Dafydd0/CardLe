package com.elteleco.hearthapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.elteleco.hearthapp.databinding.ActivityMainBinding
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var appUpdate: AppUpdateManager? = null
    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //CardLe con colores
        val word: Spannable = SpannableString("Card")
        word.setSpan(
            ForegroundColorSpan(Color.parseColor("#FFFFFFFF")),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.appNameTV.text = word
        val wordTwo: Spannable = SpannableString("Le")

        wordTwo.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF0266")),
            0,
            wordTwo.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.appNameTV.append(wordTwo)

        appUpdate = AppUpdateManagerFactory.create(this)
        checkUpdate()

/*
        // Creates a button that mimics a crash when pressed
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
*/

        var language = "English"
        var set = "Standard"


        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapterLanguages = ArrayAdapter(this, R.layout.list_item, languages)
        binding.autocompleteTextViewLanguages.setAdapter(arrayAdapterLanguages)
        binding.autocompleteTextViewLanguages.setOnItemClickListener { parent, view, position, id ->

            language = parent?.getItemAtPosition(position).toString()
            Log.d("DEBUG", language)
        }

        val sets = resources.getStringArray(R.array.sets)
        val arrayAdapterSets = ArrayAdapter(this, R.layout.list_item, sets)
        binding.autocompleteTextViewSet.setAdapter(arrayAdapterSets)
        binding.autocompleteTextViewSet.setOnItemClickListener { parent, view, position, id ->

            set = parent?.getItemAtPosition(position).toString()
            Log.d("DEBUG", set)
        }


        binding.continueBTN.setOnClickListener {
            val intent = Intent(this, GuessGameActivity::class.java).apply {
                putExtra("counter", 5)
                putExtra("language", language)
                putExtra("set", set)
            }
            startActivity(intent)
        }
    }


    private fun checkUpdate() {
        appUpdate?.appUpdateInfo?.addOnSuccessListener { updateInfo ->

            if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && updateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                Log.d("DEBUG", "Update available")
                appUpdate?.startUpdateFlowForResult(
                    updateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    REQUEST_CODE
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        inProgressUpdate()
    }

    private fun inProgressUpdate() {
        appUpdate?.appUpdateInfo?.addOnSuccessListener { updateInfo ->

            if (updateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                && updateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                Log.d("DEBUG", "Update available")
                appUpdate?.startUpdateFlowForResult(
                    updateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    REQUEST_CODE
                )
            }
        }
    }
}