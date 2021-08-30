package com.ogoons.clearabletextinputedittextdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.textfield.TextInputEditText

class ClearableTextInputEditText(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs), View.OnFocusChangeListener, TextWatcher {

    var isClearButtonVisibleFocused = false

    var onClickClearButtonListener: OnClickClearButtonListener? = null

    var clearButtonDrawable: Drawable? = null
        set(value) {
            value?.setBounds(0, 0, value.intrinsicWidth, value.intrinsicHeight)
            field = value
            setClearButtonVisibility(text?.isNotEmpty() ?: false)
        }

    init {
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        x?.let { x_ ->
            clearButtonDrawable?.let {
                if (it.isVisible && x_ > width - paddingRight - it.intrinsicWidth) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        error = null
                        text = null
                        onClickClearButtonListener?.onClick(this)
                        return true
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun setClearButtonVisibility(isVisible: Boolean) {
        clearButtonDrawable?.setVisible(isVisible, false)
        setCompoundDrawables(
            null,
            null,
            if (clearButtonDrawable != null && isVisible) clearButtonDrawable else null,
            null
        )
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (isClearButtonVisibleFocused) {
            if (hasFocus) {
                setClearButtonVisibility(text?.isNotEmpty() ?: false)
            } else {
                setClearButtonVisibility(false)
            }
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        if (isClearButtonVisibleFocused) {
            setClearButtonVisibility(isFocused && !text.isNullOrEmpty())
        } else {
            setClearButtonVisibility(!text.isNullOrEmpty())
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    interface OnClickClearButtonListener {

        fun onClick(v: View)
    }
}