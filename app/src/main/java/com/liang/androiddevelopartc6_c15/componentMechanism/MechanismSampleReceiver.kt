package com.liang.androiddevelopartc6_c15.componentMechanism

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MechanismSampleReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "MechanismSampleReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive:${intent.action}")
    }
}
