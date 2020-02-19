package com.noname.crenol

import android.graphics.*
import com.crenol.myapplication.player.Player

class Game(val p1n: Player, val p2n : Player){
    var p1 : Player = p1n
    var p2 : Player = p2n

    var winner: Int? = null
    var psize: Int = 3
    var needToWin : Int = 5
    var pole: MutableList<MutableList<Int>> = MutableList(psize, { MutableList(psize, {0})})

    var w : Int = psize
    var h : Int = psize

    //var pole: Array<Array<Int>> = Array(psize, { Array(psize, {0})})
    var turn: Int = 0
    var thisPlayer: Int = 0

    //pol.add( MutableList(pol.size, {0} ) )

    fun addrow(dir: String)
    {
        if (dir == "b")
        {
            pole.add( MutableList(pole[0].size, {0} ) ) // WORKS
        }
        if (dir == "t")
        {
            pole.add(0, MutableList(pole[0].size, {0} ) ) // WORKS
        }
        if (dir == "r")
        {
            for (i in 0..pole.size-1) {
                pole[i].add(  0  )
            }
        }
        if (dir == "l")
        {
            for (i in 0..pole.size-1) {
                pole[i].add(0, 0)
            }
        }
    }

    fun checkborder()
    {
        for (i in 0..pole.size-1)
        {
            if (pole[i][0]!=0)
            {
                addrow("l")
            }
        }

        for (i in 0..pole.size-1)
        {
            if (pole[i][pole[0].size-1]!=0)
            {
                addrow("r")
            }
        }

        for (i in 0..pole[0].size-1)
        {
            if (pole[0][i]!=0)
            {
                addrow("t")
            }
        }

        for (i in 0..pole[0].size-1)
        {
            if (pole[pole.size-1][i]!=0)
            {
                addrow("b")
            }
        }
    }

    fun check( ) : Boolean
    { //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.psize-1) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }
        return false
    }

    fun draw(canvas: Canvas){
        var brus: Paint = Paint()
        brus.strokeWidth = 20f
        for (i in 0..pole[0].size){
            canvas.drawLine(i*(canvas.width/pole[0].size).toFloat(), 0f, i*(canvas.width/pole[0].size).toFloat(),
                canvas.height.toFloat(),brus)

        }
        for (i in 0..pole.size) {
            canvas.drawLine(
                0f, i * (canvas.height / pole.size).toFloat(),
                canvas.width.toFloat(),i * (canvas.height/ pole.size).toFloat(), brus )
        }

        for (x in 0..pole.size-1)
        {
            for (y in 0..pole[0].size-1)
            {
                if (pole[x][y] == 1)
                {
                    brus.color = Color.BLUE  //BLUE O
                    canvas.drawCircle(y.toFloat() * (canvas.width / pole[0].size) + canvas.width / pole[0].size / 2  ,x.toFloat() * (canvas.height / pole.size) + canvas.height / pole.size / 2, (canvas.width / pole[0].size / 2).toFloat(), brus)
                }
                else if (pole[x][y] == 2)
                {
                    brus.color = Color.RED  //RED X
                    canvas.drawLine(y.toFloat() * (canvas.width / pole[0].size), x.toFloat() * (canvas.height / pole.size), (y+1).toFloat() * (canvas.width / pole[0].size), (x+1).toFloat() * (canvas.height / pole.size), brus)
                    canvas.drawLine((y+1).toFloat() * (canvas.width / pole[0].size), x.toFloat() * (canvas.height / pole.size), y.toFloat() * (canvas.width / pole[0].size), (x+1).toFloat() * (canvas.height / pole.size), brus)
                }
            }
        }
        checkline(canvas)
    }

    fun checkline(canvas : Canvas) : Boolean
    { //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), brus)
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine(((l-needToWin).toFloat() + 1.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), (l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.psize-1) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), (l.toFloat() + 0.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }
        return false
    }


    fun cleartheroof()
    {
        this.pole =  MutableList(psize, { MutableList(psize, {0})})
        this.turn = 0
    }


}