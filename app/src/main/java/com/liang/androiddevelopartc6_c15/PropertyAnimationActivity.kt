package com.liang.androiddevelopartc6_c15

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class PropertyAnimationActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "PropertyAnimation"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)

        val view = findViewById(R.id.tv_hw)
        view.setOnClickListener({ Log.d(TAG, "onClick") })
//        testCommonPropertyAnimation(view)
//        displayAnimationByXmlConfiguration(view, R.animator.property_animator)
        testInterpolatorAndEvaluator(view)

//        view.animate().x(90f).alpha(0.25f)
    }

    fun testCommonPropertyAnimation(view: View) {
        val set = AnimatorSet()
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0f, 90f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0.25f, 1f)
        )
        set.setDuration(3000).start()
    }

    fun displayAnimationByXmlConfiguration(view: View, resId: Int) {
        val set = AnimatorInflater.loadAnimator(this, resId) as AnimatorSet
        set.setTarget(view)
        set.start()
    }

    fun testInterpolatorAndEvaluator(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0f, 300f)
        animator.duration = 3000
//        animator.interpolator = LinearInterpolator()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }
}
