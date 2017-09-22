package com.liang.androiddevelopartc6_c15.threadDemo

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.liang.androiddevelopartc6_c15.R
import java.text.SimpleDateFormat
import java.util.*

class AsyncTaskSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AsyncTaskSample"
    }

    private lateinit var downloadAsyncTask: DownloadFileAsyncTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_sample)

        findViewById(R.id.btn_async_task_demo).setOnClickListener {
            downloadAsyncTask = DownloadFileAsyncTask()
            downloadAsyncTask.execute("")
        }

        findViewById(R.id.btn_serial_async_task).setOnClickListener{
            asyncTaskOnSerial()
        }

        findViewById(R.id.btn_parallel_async_task).setOnClickListener {
            asyncTaskOnParallel()
        }
    }

    override fun onPause() {
        super.onPause()
        downloadAsyncTask.cancel(true)
    }


    private fun asyncTaskOnParallel() {
        Log.d(TAG, "---------async task on parallel---------")
        Log.d(TAG, "cpu count ${Runtime.getRuntime().availableProcessors()}")
        MyAsyncTask("task-1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "task-1-ss")
        MyAsyncTask("task-2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"task-2-ss")
        MyAsyncTask("task-3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"task-3-ss")
        MyAsyncTask("task-4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"task-4-ss")
        MyAsyncTask("task-5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"task-5-ss")
    }

    private fun asyncTaskOnSerial() {
        Log.d(TAG, "---------async task on serial---------")
        MyAsyncTask("task-1").execute("task-1-ss")
        MyAsyncTask("task-2").execute("task-2-ss")
        MyAsyncTask("task-3").execute("task-3-ss")
        MyAsyncTask("task-4").execute("task-4-ss")
        MyAsyncTask("task-5").execute("task-5-ss")
    }

    class MyAsyncTask(private val name: String) : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            Log.d(TAG, "doInBackground/${params[0]}")
            Thread.sleep(3000)
            return name
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.d(TAG, "onProgressUpdate/${values[0]}")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
            Log.d(TAG, "onPostExecute/${sdf.format(Date())}/$result")
        }
    }

    class DownloadFileAsyncTask : AsyncTask<String, Int, Int>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute.../${Thread.currentThread().name}")
        }

        override fun doInBackground(vararg params: String?): Int {
            Log.d(TAG, "doInBackground.../${Thread.currentThread().name}")
            var progress = 0
            while (progress < 10) {
                Thread.sleep(1000)
                progress ++
                publishProgress(progress * 10)
            }

            return progress * 10
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.d(TAG, "onProgressUpdate.../${values[0]}/${Thread.currentThread().name}")

        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute.../$result/${Thread.currentThread().name}")
        }

        override fun onCancelled(result: Int?) {
            super.onCancelled(result)
            Log.d(TAG, "onCancelled.../$result/${Thread.currentThread().name}")
        }

    }


}

