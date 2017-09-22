package com.liang.androiddevelopartc6_c15.componentMechanism

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MechanismSampleService : Service() {
    companion object {
        private const val TAG = "MechanismSampleService"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind")
        return null
    }
}
