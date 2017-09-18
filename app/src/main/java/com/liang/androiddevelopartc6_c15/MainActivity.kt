package com.liang.androiddevelopartc6_c15

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.VERTICAL
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG : String = "MainActivity"
        private const val CATEGORY_SAMPLE : String = "com.liang.androiddevelopartc6_c15.sample.SAMPLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val textView = findViewById(R.id.tv_hw)
//        val td: TransitionDrawable = textView.background as TransitionDrawable
//        td.startTransition(3000)

        val recyclerView = findViewById(R.id.resolve_views) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = ResolveInfoAdapter(this, getResolves())
    }

    fun getResolves() : List<ResolveInfo> {
        val intent = Intent()
        intent.action = Intent.ACTION_RUN
        intent.addCategory(CATEGORY_SAMPLE)
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    }

    fun toActivity(resolve: ResolveInfo) {
        val acInfo = resolve.activityInfo
        val componentName = ComponentName(acInfo.applicationInfo.packageName, acInfo.name)
        Log.d(TAG, "packageName/activityName : ${acInfo.applicationInfo.packageName}-/-${acInfo.name}")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.component = componentName
        startActivity(intent)
    }

    internal inner class ResolveInfoAdapter(context : Context, private val resolves : List<ResolveInfo>) : Adapter<ResolveInfoAdapter.ResolveInfoViewHolder>() {
        private val inflater : LayoutInflater = LayoutInflater.from(context)
        private val pm : PackageManager = context.packageManager

        override fun getItemCount(): Int {
            return resolves.size
        }

        override fun onBindViewHolder(holder: ResolveInfoViewHolder, position: Int) {
            holder.label.text = resolves[position].loadLabel(pm)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResolveInfoViewHolder {
            return ResolveInfoViewHolder(inflater.inflate(R.layout.resolve_item, parent, false))
        }

        internal inner class ResolveInfoViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            val label : TextView = view.findViewById(R.id.tv_label) as TextView
            init {
                view.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                toActivity(resolves[adapterPosition])
            }
        }
    }

}
