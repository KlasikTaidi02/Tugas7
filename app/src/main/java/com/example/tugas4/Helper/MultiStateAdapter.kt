package com.example.tugas4.Helper

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.tugas4.R
import com.kennyc.view.MultiStateView

class UIUtils {
}

fun MultiStateView.showContentState() {
    this.viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showLoadingState() {
    this.viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.showErrorState(
    errorMessage: String,
    errorAction: (() -> Unit)? = null
) {
    val stateNow = MultiStateView.ViewState.ERROR

    val tvDefaultError = this.getView(stateNow)?.findViewById<TextView>(R.id.tv_default_error)

    if (tvDefaultError != null) {
        tvDefaultError.text = errorMessage
    }

    getView(stateNow)?.findViewById<Button>(R.id.btn_default_error)?.setOnClickListener {
        errorAction?.invoke()
    }

}

fun MultiStateView.showEmptyState(
    emptyMessage: String,
    emptyImage: Drawable? = null
) {
    val stateNow = MultiStateView.ViewState.EMPTY

    val tvDefaultEmpty = this.getView(stateNow)?.findViewById<TextView>(R.id.tv_default_empty)

    if (tvDefaultEmpty != null) {
        tvDefaultEmpty.text = emptyMessage
    }

    getView(stateNow)?.findViewById<ImageView>(R.id.iv_default_empty)?.setImageDrawable(emptyImage)

}