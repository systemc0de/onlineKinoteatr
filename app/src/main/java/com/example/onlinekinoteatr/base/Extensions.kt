package com.example.onlinekinoteatr.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder


const val DEFAULT_THROTTLE_DELAY = 200L

fun View.setThrottledClickListener(delay: Long = DEFAULT_THROTTLE_DELAY, onClick: (View) -> Unit) {
    setOnClickListener {
        throttle(delay) {
            onClick(it)
        }
    }
}

private var lastClickTimestamp = 0L
fun View.throttle(delay: Long = DEFAULT_THROTTLE_DELAY, action: () -> Unit): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val delta = currentTimestamp - lastClickTimestamp
    if (delta !in 0L..delay) {
        lastClickTimestamp = currentTimestamp
        action()
        return true
    }
    return false
}

fun ImageView.loadImage(
    src: String?,
//    @DrawableRes errorRes: Int = R.drawable.ic_image_not_found,
//    @DrawableRes placeholderRes: Int = R.drawable.ic_placeholder,
    config: RequestBuilder<Drawable>.() -> Unit = {}
) {
    Glide
        .with(context)
        .load(src)
//        .error(errorRes)
//        .placeholder(placeholderRes)
        .apply { config(this) }
        .into(this)
}