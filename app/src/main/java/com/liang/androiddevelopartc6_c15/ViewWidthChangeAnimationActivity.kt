package com.liang.androiddevelopartc6_c15

import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class ViewWidthChangeAnimationActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ViewWidthChange"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_width_change_animation)
        val view = findViewById(R.id.tv_hw)
        ObjectAnimator.ofInt(WrapView(view), "width", 200, 500)
                .setDuration(5000)
                .start()
//        changeWidthByUpdateListener(view, 200, 500)
    }

    inner class WrapView(v: View) {
        private val view = v

        fun getWidth(): Int {
            return view.layoutParams.width
        }

        fun setWidth(width: Int) {
            view.layoutParams.width = width
            view.requestLayout()
        }
    }

    fun changeWidthByUpdateListener(view: View, start: Int, end: Int) {
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener(WidthAnimationListener(view, start, end))
        animator.setDuration(3000).start()
    }

    inner class WidthAnimationListener(private val view: View, private val start: Int, private val end: Int) : ValueAnimator.AnimatorUpdateListener {
        private val evaluator = IntEvaluator()

        override fun onAnimationUpdate(animator: ValueAnimator?) {
            Log.d(TAG, animator?.animatedValue.toString())
            Log.d(TAG, animator?.animatedFraction.toString())
            val width = evaluator.evaluate(animator!!.animatedFraction, start, end)
            view.layoutParams.width = width
            view.requestLayout()
        }

    }
}
