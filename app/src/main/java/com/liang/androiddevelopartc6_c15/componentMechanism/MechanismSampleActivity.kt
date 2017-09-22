package com.liang.androiddevelopartc6_c15.componentMechanism

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.liang.androiddevelopartc6_c15.R
import kotlinx.android.synthetic.main.activity_mechanism_sample.*

class MechanismSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MechanismSampleActivity"
        private const val RECEIVER_ACTION = "com.liang.androiddevelopartc6_c15.receiver_LAUNCH"
        private const val CONTENT_PROVIDER_AUTHORITY = "com.androiddevelopart.provider"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanism_sample)

        // dynamic register broadcast receiver
//        registerBR()

        findViewById(R.id.btn_start_service).setOnClickListener {
            startService(Intent(this, MechanismSampleService::class.java))
        }

        findViewById(R.id.btn_bind_service).setOnClickListener {
            bindService(Intent(this, MechanismSampleService::class.java), object : ServiceConnection {
                override fun onServiceDisconnected(name: ComponentName?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }, Context.BIND_AUTO_CREATE)
        }

        findViewById(R.id.btn_send_broadcast).setOnClickListener { sendBroadcast(Intent(RECEIVER_ACTION)) }

        this.btn_query_provider.setOnClickListener{
            contentResolver.query(Uri.parse("content://" + CONTENT_PROVIDER_AUTHORITY), null, null, null, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterBR()
    }


    val receiver = MechanismSampleReceiver()
    private fun registerBR() {
        registerReceiver(receiver, IntentFilter(RECEIVER_ACTION))
    }

    private fun unregisterBR() {
        unregisterReceiver(receiver)
    }
}
