package com.devpadawan.memorygame

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cards_image.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var turno = 1
    private var pointsPlayer1 = 0
    private var pointsPlayer2 = 0
    private var firstCard = 0
    private var secondCard = 0
    private var firstClick = 0
    private var secondClick = 0
    private var cardNumber = 1

    private var cards = ArrayList<Int>(listOf(11, 12, 13, 14, 15, 21, 22, 23, 24, 25))

    private var img11 = 0
    private var img12 = 0
    private var img13 = 0
    private var img14 = 0
    private var img15 = 0
    private var img21 = 0
    private var img22 = 0
    private var img23 = 0
    private var img24 = 0
    private var img25 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Set up User Interface
        setUpUI()

        //2. Set up cards
        setUpCards()

        //3.Shuffle cards
        cards.shuffle()

        //4.Set up imageView onClickListener
        setUpOnClickListener()
    }

    private fun setUpUI() {
        txt_player_1.setTextColor(Color.GREEN)
        txt_player_1.setTypeface(null, Typeface.BOLD)

        txt_player_2.setTextColor(Color.GRAY)
        txt_player_2.setTypeface(null, Typeface.NORMAL)
    }

    private fun setUpCards(){
        img11 = R.drawable.ic_burguer
        img12 = R.drawable.ic_flower
        img13 = R.drawable.ic_rabbit
        img14 = R.drawable.ic_surf
        img15 = R.drawable.ic_rat

        img21 = R.drawable.ic_burguer
        img22 = R.drawable.ic_flower
        img23 = R.drawable.ic_rabbit
        img24 = R.drawable.ic_surf
        img25 = R.drawable.ic_rat
    }

    private fun setUpOnClickListener() {
        img1_1.setOnClickListener {
            val card = 0
            assignImageCard(img1_1, card)
        }
        img1_2.setOnClickListener {
            val card = 1
            assignImageCard(img1_2, card)
        }
        img1_3.setOnClickListener {
            val card = 2
            assignImageCard(img1_3, card)
        }
        img2_1.setOnClickListener {
            val card = 3
            assignImageCard(img2_1, card)
        }
        img2_2.setOnClickListener {
            val card = 4
            assignImageCard(img2_2, card)
        }
        img2_3.setOnClickListener {
            val card = 5
            assignImageCard(img2_3, card)
        }
        img3_1.setOnClickListener {
            val card = 6
            assignImageCard(img3_1, card)
        }
        img3_2.setOnClickListener {
            val card = 7
            assignImageCard(img3_2, card)
        }
        img3_3.setOnClickListener {
            val card = 8
            assignImageCard(img3_3, card)
        }
        img4_1.setOnClickListener {
            val card = 9
            assignImageCard(img4_1, card)
        }
    }

    private fun assignImageCard(img: ImageView, card: Int) {
        when(cards[card]){
            11 -> img.setImageResource(img11)
            12 -> img.setImageResource(img12)
            13 -> img.setImageResource(img13)
            14 -> img.setImageResource(img14)
            15 -> img.setImageResource(img15)
            21 -> img.setImageResource(img21)
            22 -> img.setImageResource(img22)
            23 -> img.setImageResource(img23)
            24 -> img.setImageResource(img24)
            25 -> img.setImageResource(img25)
        }

        if(cardNumber == 1){
            firstCard = cards[card]
            if (firstCard > 20){
                firstCard -= 10
            }
            cardNumber = 2
            firstClick = card
            img.isEnabled = false

        }else if(cardNumber == 2 ){
            secondCard = cards[card]
            if(secondCard > 20){
                secondCard -= 10
            }
            cardNumber = 1
            secondClick = card

            img1_1.isEnabled = false
            img1_2.isEnabled = false
            img1_3.isEnabled = false
            img2_1.isEnabled = false
            img2_2.isEnabled = false
            img2_3.isEnabled = false
            img3_1.isEnabled = false
            img3_2.isEnabled = false
            img3_3.isEnabled = false
            img4_1.isEnabled = false

            Handler(Looper.getMainLooper())
                .postDelayed({
                    checkCoincidence()
                }, 1000)
        }
    }

    private fun checkCoincidence() {
        if (firstCard == secondCard) {
            when (firstClick) {
                0 -> img1_1.visibility = View.INVISIBLE
                1 -> img1_2.visibility = View.INVISIBLE
                2 -> img1_3.visibility = View.INVISIBLE
                3 -> img2_1.visibility = View.INVISIBLE
                4 -> img2_2.visibility = View.INVISIBLE
                5 -> img2_3.visibility = View.INVISIBLE
                6 -> img3_1.visibility = View.INVISIBLE
                7 -> img3_2.visibility = View.INVISIBLE
                8 -> img3_3.visibility = View.INVISIBLE
                9 -> img4_1.visibility = View.INVISIBLE
            }
            when (secondClick) {
                0 -> img1_1.visibility = View.INVISIBLE
                1 -> img1_2.visibility = View.INVISIBLE
                2 -> img1_3.visibility = View.INVISIBLE
                3 -> img2_1.visibility = View.INVISIBLE
                4 -> img2_2.visibility = View.INVISIBLE
                5 -> img2_3.visibility = View.INVISIBLE
                6 -> img3_1.visibility = View.INVISIBLE
                7 -> img3_2.visibility = View.INVISIBLE
                8 -> img3_3.visibility = View.INVISIBLE
                9 -> img4_1.visibility = View.INVISIBLE
            }

            if (turno == 1) {
                pointsPlayer1++
                val points = "Player1: $pointsPlayer1"
                txt_player_1.text = points
            } else {
                pointsPlayer2++
                val points = "Player2: $pointsPlayer2"
                txt_player_2.text = points
            }
        } else {
            img1_1.setImageResource(R.drawable.ic_bolt)
            img1_2.setImageResource(R.drawable.ic_bolt)
            img1_3.setImageResource(R.drawable.ic_bolt)
            img2_1.setImageResource(R.drawable.ic_bolt)
            img2_2.setImageResource(R.drawable.ic_bolt)
            img2_3.setImageResource(R.drawable.ic_bolt)
            img3_1.setImageResource(R.drawable.ic_bolt)
            img3_2.setImageResource(R.drawable.ic_bolt)
            img3_3.setImageResource(R.drawable.ic_bolt)
            img4_1.setImageResource(R.drawable.ic_bolt)

            if (turno == 1) {
                turno = 2
                txt_player_1.setTextColor(Color.GRAY)
                txt_player_1.setTypeface(null, Typeface.NORMAL)

                txt_player_2.setTextColor(Color.RED)
                txt_player_2.setTypeface(null, Typeface.BOLD)
            } else {
                turno = 1
                txt_player_1.setTextColor(Color.GREEN)
                txt_player_1.setTypeface(null, Typeface.BOLD)

                txt_player_2.setTextColor(Color.GRAY)
                txt_player_2.setTypeface(null, Typeface.NORMAL)
            }
        }

        img1_1.isEnabled = true
        img1_2.isEnabled = true
        img1_3.isEnabled = true
        img2_1.isEnabled = true
        img2_2.isEnabled = true
        img2_3.isEnabled = true
        img3_1.isEnabled = true
        img3_2.isEnabled = true
        img3_3.isEnabled = true
        img4_1.isEnabled = true

        gameOver()
    }

    private fun gameOver() {
        if(img1_1.visibility == View.INVISIBLE &&
            img1_2.visibility == View.INVISIBLE &&
            img1_3.visibility == View.INVISIBLE &&
            img2_1.visibility == View.INVISIBLE &&
            img2_2.visibility == View.INVISIBLE &&
            img2_3.visibility == View.INVISIBLE &&
            img3_1.visibility == View.INVISIBLE &&
            img3_2.visibility == View.INVISIBLE &&
            img3_3.visibility == View.INVISIBLE &&
            img4_1.visibility == View.INVISIBLE ){

            val alertDialog = AlertDialog.Builder(this@MainActivity).create()

            alertDialog.setTitle("Game over")
            alertDialog.setMessage("Player 1: $pointsPlayer1  Player2: $pointsPlayer2")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK"){
                    _, _ ->
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            alertDialog.show()
        }
    }
}