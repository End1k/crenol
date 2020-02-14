package com.noname.crenol

import android.content.pm.ActivityInfo
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.crenol.myapplication.controller.PlayerVsPlayerController
import com.crenol.myapplication.player.RealPlayer
import kotlinx.android.synthetic.main.activity_main.*

class vsRealPlayerActivity : AppCompatActivity() {

    var playe1 : RealPlayer =
        RealPlayer()
    var playe2 : RealPlayer =
        RealPlayer()

    var g : Game = Game(playe1, playe2)
    var sd : PlayerVsPlayerController =
        PlayerVsPlayerController(g)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        playe1.pTurn = 0
        playe2.pTurn = 1

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
        g.psize = 3
        g.w = g.psize
        g.h = g.psize
        g.needToWin = 5
        g.pole = MutableList(g.psize, { MutableList(g.psize, {0})})
        g.turn = 0
    }

}