package com.example.foodstand.util

import android.widget.ImageView
import coil.load


object ViewExt {
    fun ImageView.convertUrlToImage(imgUrl: String){
        this.load(imgUrl){
            crossfade(600)
        }
    }
}