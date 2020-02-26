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

    var asize: Int = psize * 2

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
        asize += 1
    }

    fun checkborder()
    {
        var num : Int = 0
        for (i in 0..pole.size-1)
        {
            if (pole[i][0]!=0) { num++ }
        }
        if (num >= (pole.size+1)/2)  { addrow("l") }

        num = 0
        for (i in 0..pole.size-1)
        {
            if (pole[i][pole[0].size-1]!=0) { num++ }
        }
        if (num >= (pole.size+1)/2) { addrow("r") }

        num = 0
        for (i in 0..pole[0].size-1)
        {
            if (pole[0][i]!=0) { num++ }
        }
        if (num >= (pole[0].size+1)/2) { addrow("t") }

        num = 0
        for (i in 0..pole[0].size-1)
        {
            if (pole[pole.size-1][i]!=0) { num++ }
        }
        if (num >= (pole[0].size+1)/2) { addrow("b") }
    }

    fun check() : Boolean
    { //диагонали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in 0..this.pole[0].size-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p || pole[i+n][l+n] == p+2) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.pole[0].size-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p || pole[i+n][l-n] == p+2) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.pole.size-1) { //столбцы
            for (l in 0..this.pole[0].size-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p || pole[i][l+n] == p+2) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in 0..this.pole[0].size-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p || pole[i+n][l] == p+2) {v++}
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
        canvas.drawColor(Color.LTGRAY)
        val padding = (canvas.width/5/asize)
        var brus: Paint = Paint()
        brus.strokeWidth = (canvas.width/3/asize).toFloat()
        //brus.strokeWidth = 20f
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
                    brus.color = Color.rgb(59,70,171)  //BLUE O
                    canvas.drawCircle(y.toFloat() * (canvas.width / pole[0].size) + canvas.width / pole[0].size / 2  ,x.toFloat() * (canvas.height / pole.size) + canvas.height / pole.size / 2, (canvas.width / pole[0].size / 2).toFloat(), brus)
                }
                else if (pole[x][y] == 2)
                {
                    brus.color = Color.parseColor("#d50000")  //RED X
                    canvas.drawLine(y.toFloat() * (canvas.width / pole[0].size) + padding, x.toFloat() * (canvas.height / pole.size) + padding, (y+1).toFloat() * (canvas.width / pole[0].size) - padding, (x+1).toFloat() * (canvas.height / pole.size) - padding, brus)
                    canvas.drawLine((y+1).toFloat() * (canvas.width / pole[0].size) - padding, x.toFloat() * (canvas.height / pole.size) + padding, y.toFloat() * (canvas.width / pole[0].size) + padding, (x+1).toFloat() * (canvas.height / pole.size) - padding, brus)
                }
                else if (pole[x][y] == 3)
                {
                    brus.color = Color.rgb(18,134,18)  //BLUE O
                    canvas.drawCircle(y.toFloat() * (canvas.width / pole[0].size) + canvas.width / pole[0].size / 2  ,x.toFloat() * (canvas.height / pole.size) + canvas.height / pole.size / 2, (canvas.width / pole[0].size / 2).toFloat(), brus)
                }
                else if (pole[x][y] == 4)
                {
                    brus.color = Color.rgb(18,134,18)  //RED X
                    canvas.drawLine(y.toFloat() * (canvas.width / pole[0].size) + padding, x.toFloat() * (canvas.height / pole.size) + padding, (y+1).toFloat() * (canvas.width / pole[0].size) - padding, (x+1).toFloat() * (canvas.height / pole.size) - padding, brus)
                    canvas.drawLine((y+1).toFloat() * (canvas.width / pole[0].size) - padding, x.toFloat() * (canvas.height / pole.size) + padding, y.toFloat() * (canvas.width / pole[0].size) + padding, (x+1).toFloat() * (canvas.height / pole.size) - padding, brus)
                }
            }
        }
        checkline(canvas)
    }

    fun checkline(canvas : Canvas) : Boolean
    {
        var brus : Paint = Paint()
        brus.color = Color.GREEN  //RED X
        brus.strokeWidth = (canvas.width/2/asize).toFloat()
        //диагонали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in 0..this.pole[0].size-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p || pole[i+n][l+n] == p+2) {v++}
                    }
                    if (v == needToWin) {

                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / pole[0].size), (i.toFloat() + 0.5f) * (canvas.height / pole.size), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / pole[0].size), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / pole.size), brus)
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.pole[0].size-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p || pole[i+n][l-n] == p+2) {v++}
                    }
                    if (v == needToWin) {

                        canvas.drawLine(((l-needToWin).toFloat() + 1.5f) * (canvas.width / pole[0].size), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / pole.size), (l.toFloat() + 0.5f) * (canvas.width / pole[0].size), (i.toFloat() + 0.5f) * (canvas.height / pole.size), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.pole.size-1) { //столбцы
            for (l in 0..this.pole[0].size-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p || pole[i][l+n] == p+2) {v++}
                    }
                    if (v == needToWin) {

                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / pole[0].size), (i.toFloat() + 0.5f) * (canvas.height / pole.size), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / pole[0].size), (i.toFloat() + 0.5f) * (canvas.height / pole.size), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.pole.size-this.needToWin) { //столбцы
            for (l in 0..this.pole[0].size-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p || pole[i+n][l] == p+2) {v++}
                    }
                    if (v == needToWin) {

                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / pole[0].size), (i.toFloat() + 0.5f) * (canvas.height / pole.size), (l.toFloat() + 0.5f) * (canvas.width / pole[0].size), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / pole.size), brus)
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