package com.crenol.myapplication.player

import android.graphics.Point
import com.noname.crenol.Game


class RealPlayer : Player(){
    var thisTurnChoice : Point = Point()

    override fun turning(g: Game) : Point
    {

        return Point()
    }

    fun setPoint(){

    }


//    fun roundUser(g : Game, t: PointF){
//        if (this.pole[t.x.toInt()][t.y.toInt()] == 0 && (!check()))
//        {
//            this.pole[t.x.toInt()][t.y.toInt()] = turn % 2 + 1
//            turn++
//
//            if (this.check()) {}
//            round() ////////////////////////////////////////////////////////////////////////////////
//            if (this.check()) {}
//        }
//    }
}