package com.elteleco.hearthapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elteleco.hearthapp.databinding.ActivityWinnerBinding
import com.google.android.gms.ads.AdRequest
import com.squareup.picasso.Picasso

class WinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //loadAds()

        val bundle: Bundle? = intent.extras
        val cardUrl = bundle?.getString("cardUrl")

        if (cardUrl != null) {
            Picasso.get().load(cardUrl).fit().into(binding.cardImage)
        }

        binding.returnBTN.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.bannerAd.loadAd(adRequest)
    }
}