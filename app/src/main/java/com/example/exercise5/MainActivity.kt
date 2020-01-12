package com.example.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Module-level variable
    lateinit var countViewModel: CountViewModel

    //Create an instance of the Shared Preferences
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialise the ViewModel
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        //Initialise the Shared Preferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        var liked = false

        imageViewLike.setOnClickListener {

            if (liked)
            {
                imageViewLike.setImageResource(R.drawable.ic_thumb_up_black_24dp)
                countViewModel.countLike--
                liked = false
            }
            else
            {
                imageViewLike.setImageResource(R.drawable.ic_thumb_up_blue_24dp)
                countViewModel.countLike++
                liked = true
            }

            textViewLike.text = countViewModel.countLike.toString()
        }

        var disliked = false

        imageViewDislike.setOnClickListener {

            if (disliked)
            {
                imageViewDislike.setImageResource(R.drawable.ic_thumb_down_black_24dp)
                countViewModel.countDislike--
                disliked = false
            }
            else
            {
                imageViewDislike.setImageResource(R.drawable.ic_thumb_down_blue_24dp)
                countViewModel.countDislike++
                disliked = true
            }

            textViewDislike.text = countViewModel.countDislike.toString()
        }
    }

    override fun onStart() {

        Log.d("MainActivity" , "onStart")
        super.onStart()
    }

    override fun onResume() {

        Log.d("MainActivity" , "onResume")

        countViewModel.countLike = sharedPreferences.getInt(getString(R.string.like), 0)
        countViewModel.countDislike = sharedPreferences.getInt(getString(R.string.dislike), 0)

        textViewLike.text = countViewModel.countLike.toString()
        textViewDislike.text = countViewModel.countDislike.toString()

        super.onResume()
    }

    override fun onPause() {

        Log.d("MainActivity" , "onPause")

        with(sharedPreferences.edit())
        {
            putInt(getString(R.string.like), countViewModel.countLike)
            putInt(getString(R.string.dislike), countViewModel.countDislike)
            commit()
        }

        super.onPause()
    }

    override fun onStop() {

        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {

        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}
