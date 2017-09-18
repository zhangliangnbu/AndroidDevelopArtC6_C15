package com.liang.androiddevelopartc6_c15

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class LayoutAnimationSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation_sample)

        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = SimpleAdapter(this)

    }

    internal inner class SimpleAdapter(context:Context) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {
        private val inflater = LayoutInflater.from(context)

        private val messages = Array(30, {i -> (i*i).toString() })

        override fun onBindViewHolder(holder: SimpleViewHolder?, position: Int) {
            holder?.textView?.text = messages[position]
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimpleViewHolder {
            return SimpleViewHolder(inflater.inflate(R.layout.resolve_item, parent, false))
        }

        override fun getItemCount(): Int {
            return messages.size
        }


        internal inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView = view.findViewById(R.id.tv_label) as TextView
        }
    }
}
