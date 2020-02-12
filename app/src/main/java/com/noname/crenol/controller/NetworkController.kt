package com.crenol.myapplication.controller

import android.graphics.Point
import android.util.Log

import com.google.firebase.database.*
import com.noname.crenol.Game

class NetworkController(gn: Game, myRef : DatabaseReference) : Controller(gn) {

    var r: Boolean = true
    var Myp: Point? = null

    init {
        myRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    var p: Point? = p0.getValue(Point::class.java)
                    setPlace(p!!, getSC())
                    turni(p!!)
                    Log.d("TEST", "Q")
                    g.turn++
                    if (p == Myp){
                        r = false
                    }
                    else{
                        r = true
                    }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                g.cleartheroof()

            }
        })
    }

    //myRef.addChildEventListener

     fun lala(p : Point){

     }

    override fun turni(p: Point) {

    }
}