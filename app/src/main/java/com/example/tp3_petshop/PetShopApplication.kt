package com.example.tp3_petshop

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
class PetShopApplication: Application()  {
    override fun onCreate() {
        super.onCreate();
    }
}