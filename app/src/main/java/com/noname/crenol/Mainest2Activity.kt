package com.noname.crenol


import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.DataSetObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class Mainest2Activity : AppCompatActivity() {

    var DIALOG_EXIT : Int = 1;
    var arrayo = arrayOf("3x3","5x5","7x7","10x10","Expand mode")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainest2)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    fun toComputer(v: View)
    {
        var next: Intent = Intent(this, MainActivity::class.java)
        shoDialog(DIALOG_EXIT, next);
    }
    fun toPlayer(v: View)
    {
        var next: Intent = Intent(this, vsRealPlayerActivity::class.java)
        shoDialog(DIALOG_EXIT, next);
    }
    fun toEnternet(v: View)
    {
        var next: Intent = Intent(this, vsRealPlayerActivityEnternet::class.java)
        startActivity(next)
    }

    fun getSize(which: Int): Int
    {
        if(which==0) {return 3}
        if(which==1) {return 5}
        if(which==2) {return 7}
        if(which==3) {return 10}
        if(which==4) {return 3}
        return 10000
    }

    fun getNtw(which: Int): Int
    {
        if(which==0) {return 3}
        if(which==1) {return 5}
        if(which==2) {return 5}
        if(which==3) {return 5}
        if(which==4) {return 5}
        return 10000
    }

    fun getEx(which: Int): Int
    {
        if(which==0) {return 0}
        if(which==1) {return 0}
        if(which==2) {return 0}
        if(which==3) {return 0}
        if(which==4) {return 1}
        return 0
    }

    fun shoDialog(id: Int, next: Intent) : Dialog {
        if (id == DIALOG_EXIT) {
            val adb : AlertDialog.Builder = AlertDialog.Builder(this);
            adb.setTitle("Choose the size");
            adb.setIcon(android.R.drawable.ic_dialog_info);

            adb.setItems(arrayo, {_, which ->
                //Toast.makeText(applicationContext, arrayo[which] + " is clicked", Toast.LENGTH_SHORT).show()

                next.putExtra("size", getSize(which))
                next.putExtra("ntw", getNtw(which))
                next.putExtra("isEx", getEx(which))
                startActivity(next)
                }
            )
            val dialog = adb.create()

            dialog.show()

            adb.show()
            adb.create()
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
}
