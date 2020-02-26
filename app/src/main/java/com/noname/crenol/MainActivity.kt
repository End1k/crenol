package com.noname.crenol

import android.content.pm.ActivityInfo
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import com.crenol.myapplication.controller.PlayerVsAiController
import com.crenol.myapplication.player.AiTicTacToe
import com.crenol.myapplication.player.RealPlayer
import com.noname.crenol.R

class MainActivity : AppCompatActivity() {

    var ai1 : AiTicTacToe =
        AiTicTacToe()
    var playe : RealPlayer =
        RealPlayer()

    var g : Game = Game(ai1, playe)

    var sd : PlayerVsAiController =
        PlayerVsAiController(g)

    override fun onCreate(savedInstanceState: Bundle?) {

        g.psize = intent.getIntExtra("size", 10)
        g.asize = g.psize * 2
        g.needToWin = intent.getIntExtra("ntw", 10)

        if (intent.getIntExtra("isEx", 0)==1) {sd.isEx=true}

        g.pole = MutableList(g.psize, { MutableList(g.psize, {0})})

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ai1.pTurn = 0
        playe.pTurn = 1

        viewttt.g = g



        viewttt.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    if (p1?.action == MotionEvent.ACTION_DOWN )
                    {
                        var p : Point = Point()

                        if (p1 != null) {
                            p.x = (p1.y / (viewttt.height / g.pole.size)).toInt()
                            p.y = (p1.x / (viewttt.width / g.pole[0].size)).toInt()

                            sd.turni(p)
                        }
                    }
                    return true
                }
        })
    }
    fun bclick(v:View)
    {
        g.psize = intent.getIntExtra("size", 0)
        g.needToWin = intent.getIntExtra("ntw", 0)
        g.asize = g.psize * 2
        g.pole = MutableList(g.psize, { MutableList(g.psize, {0})})
        g.turn = 0
    }

}