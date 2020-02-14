package com.crenol.myapplication.controller

import android.graphics.Point
import com.noname.crenol.Game


class PlayerVsPlayerController(gn: Game) : Controller(gn) {
    override fun turni(p: Point) {
        if (g.pole[p.x][p.y]==0 && !g.check()) {
            setPlace(p, getC())
            g.turn++
            g.checkborder()
            //g.addrow("t")
        }
    }

}