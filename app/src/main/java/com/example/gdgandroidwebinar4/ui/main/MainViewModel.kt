package com.example.gdgandroidwebinar4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdgandroidwebinar4.models.Pony

class MainViewModel : ViewModel() {

    val ponyList = MutableLiveData<List<Pony>>().apply {
        value = listOf(
            Pony(1, "Big McIntosh", "Big Mac", "male", "Farmer\\Pony Tones bass singer", listOf()),
            Pony(2, "Fluttershy", "", "female", "Animal caretaker", listOf()),
            Pony(3, "Twilight Sparkle", "Princess Twilight Sparkle", "female", "Ruler of Equestria (S9E26)", listOf())
        )
    }

}
