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
    var arrayo = arrayOf("3x3","5x5","7x7","10x10")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainest2)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    fun toComputer(v: View)
    {
        var next: Intent = Intent(this, MainActivity::class.java)
        startActivity(next)
    }
    fun toPlayer(v: View)
    {
        shoDialog(DIALOG_EXIT);

        //var next: Intent = Intent(this, vsRealPlayerActivity::class.java)
        //startActivity(next)
    }
    fun toEnternet(v: View)
    {
        var next: Intent = Intent(this, vsRealPlayerActivityEnternet::class.java)
        startActivity(next)
    }


//   class ada:ListAdapter{
//       override fun areAllItemsEnabled(): Boolean {
//           return true
//       }
//
//       override fun isEnabled(position: Int): Boolean {
//           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//           return true
//       }
//
//       override fun getViewTypeCount(): Int {
//           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//           return 1
//       }
//
//       override fun getItemViewType(position: Int): Int {
//           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//           return 1
//           return 1
//       }
//
//       override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//           return View(this)
//       }
//
//       var arrayo = arrayOf("3x3","5x5","7x7","10x10")
//        override fun isEmpty(): Boolean {
//            return arrayo.isEmpty()
//        }
//
//
//        override fun registerDataSetObserver(observer: DataSetObserver?) {
//        }
//
//
//        override fun getItem(position: Int): Any {
//           return arrayo[position]
//        }
//
//
//
//        override fun getItemId(position: Int): Long {
//            return 2312.toLong();
//        }
//
//        override fun hasStableIds(): Boolean {
//            return true
//        }
//
//        override fun unregisterDataSetObserver(observer: DataSetObserver?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun getCount(): Int {
//           return arrayo.size
//        }
//
//    }

    fun shoDialog(id: Int) : Dialog {
        if (id == DIALOG_EXIT) {
            val adb : AlertDialog.Builder = AlertDialog.Builder(this);
            // заголовок
            adb.setTitle("Choose the size");
            // сообщение
            //adb.setMessage("Choose the size");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);

            //adb.setAdapter(ListAdapter)

            adb.setItems(arrayo, {_, which ->

                //fun onClick( dialog: DialogInterface,  which: Int) {
                    Toast.makeText(applicationContext, arrayo[which] + " is clicked", Toast.LENGTH_SHORT).show()

                //}
            }
            )

            // Create a new AlertDialog using builder object
            val dialog = adb.create()

            // Finally, display the alert dialog
            dialog.show()

            adb.show()
            adb.create()

//            adb.setItems(arrayo, DialogInterface.OnClickListener() {
//                fun onClick( dialog: DialogInterface,  which: Int) {
//                    // The 'which' argument contains the index position
//                    // of the selected item
//                }})
            // кнопка положительного ответа
            //adb.setPositiveButton(R.string.yes, myClickListener);
            // кнопка отрицательного ответа
            //adb.setNegativeButton(R.string.no, myClickListener);
            // кнопка нейтрального ответа
            //adb.setNeutralButton(R.string.cancel, myClickListener);
            // создаем диалог
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
}
