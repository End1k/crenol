package com.crenol.myapplication.controller

import android.graphics.Point
import com.noname.crenol.Game


class PlayerVsAiController(gn: Game) : Controller(gn) {
    var isEx :  Boolean = false
    override fun turni(p: Point) {
        if (p.x >= 0 && p.x <= g.pole.size-1 && p.y >= 0 && p.y <= g.pole[0].size-1)
        {
            if (g.pole[p.x][p.y] == 0 && !g.check()) {
                if (checkTurn()) {
                    setPlace(p, getC())
                    g.turn++
                    if (isEx) {
                        g.checkborder()
                    }
                    turni(getPlayer()!!.turning(g))
                } else {
                    setPlace(p, getC())
                    g.turn++
                    if (isEx) {
                        g.checkborder()
                    }
                }
            }
        }
    }

}