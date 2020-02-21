package com.crenol.myapplication.controller

import android.graphics.Point
import com.crenol.myapplication.player.Player
import com.noname.crenol.Game

open class Controller(gn: Game) {
    var g: Game = gn
    var p1 : Player?
    var p2 : Player?
    init {
        p1 = g.p1
        p2 = g.p2
    }


    var turn : Int = 0


    fun checkTurn(): Boolean{
        return (g.turn%2 == g.thisPlayer)
    }
    fun getPlayer(): Player?
    {
        if (g.turn%2 == 1) return p1
        if (g.turn%2 == 0) return p2
        return Player()
    }
    fun getC(): Int
    {
        if (g.turn%2 == 1) return 1
        if (g.turn%2 == 0) return 2
        return 0
    }

    fun getSC(): Int
    {
        if (g.turn%2 == 1) return 2
        if (g.turn%2 == 0) return 1
        return 0
    }


    fun setPlace(p :Point, char : Int){
        for (i in 0..g.pole.size-1)
            for (l in 0..g.pole[0].size-1)
                if (g.pole[i][l]>2)
                {
                    g.pole[i][l] -= 2
                }
        g.pole[p.x][p.y] = char+2
    }

    open fun turni(p: Point) {

    }
}