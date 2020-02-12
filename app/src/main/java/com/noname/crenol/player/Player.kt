package com.crenol.myapplication.player

import android.graphics.Point
import com.noname.crenol.Game

open class Player() {
    var pTurn = -1

    open fun turning(g: Game) : Point
    {
        return Point()
    }

}