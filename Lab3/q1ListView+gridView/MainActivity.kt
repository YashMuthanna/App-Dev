package com.example.app1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var modalList = ArrayList<Modal>()
    var names = arrayOf("dog1", "dog2", "dog3", "dog4", "dog5", "dog6", "dog7")
    var images = intArrayOf(R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items = arrayOf("C Programming", "Java", "PHP", "Python", "C++", "Ruby")

        val myAdapter = ArrayAdapter(this, R.layout.singlerow, items)
        val lData:ListView = findViewById(R.id.list)
        lData.adapter = myAdapter

        for(i in names.indices){
            modalList.add(Modal(names[i], images[i]))
        }
        var customAdapter = CustomAdapter(modalList, this)
        val grid = findViewById<GridView>(R.id.grid)
        grid.adapter = customAdapter

    }
    class CustomAdapter(
        var itemModel: ArrayList<Modal>,
        var context: Context

    ) : BaseAdapter(){
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getCount(): Int {
            return itemModel.size
        }

        override fun getItem(position: Int): Any {
            return itemModel[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView
            if(view == null){
                view = layoutInflater.inflate(R.layout.griditem, parent, false)

            }
            var imgName = view?.findViewById<TextView>(R.id.imgTxt)
            var imgView = view?.findViewById<ImageView>(R.id.img)

            imgName?.text = itemModel[position].name
            imgView?.setImageResource(itemModel[position].image!!)

            return view!!;
        }

    }

}
