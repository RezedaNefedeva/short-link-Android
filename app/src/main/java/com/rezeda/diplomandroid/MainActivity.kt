package com.rezeda.diplomandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var long_link: EditText = findViewById(R.id.long_link_input)
        var small_link: EditText = findViewById(R.id.small_link_input)
        var btn:Button = findViewById(R.id.button)
        val items = arrayListOf<Any>()
        var mListView = findViewById<ListView>(R.id.list_links)

        btn.setOnClickListener{
            var small_link_text: String = small_link.text.toString()
            val arrayAdapter: ArrayAdapter<*>

            if(long_link.text.toString().isEmpty()){
                btn.setText("Введите ссылку")
            } else if (small_link.text.toString().isEmpty()){
                btn.setText("Введите сокращенную ссылку")
            } else if(!items.contains("Сокращение: $small_link_text")) {
            arrayAdapter = ArrayAdapter(this, R.layout.item_style, items)
            arrayAdapter.add("Сокращение: $small_link_text")
                mListView.adapter = arrayAdapter
                mListView.setOnItemClickListener { _, _, _, _ ->
                    var long_link_text = long_link.text.toString()
                    var i = Intent(android.content.Intent.ACTION_VIEW)
                    i.data = Uri.parse("http://$long_link_text")
                    startActivity(i)
                }
                btn.setText("Готово")
            } else if (items.contains("Сокращение: $small_link_text")) {
                btn.setText("Укажите другую скоращенную ссылку")
            }
        }

    }
}