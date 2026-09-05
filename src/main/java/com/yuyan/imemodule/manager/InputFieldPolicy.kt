package com.yuyan.imemodule.manager

import android.text.InputType
import android.view.inputmethod.EditorInfo

/**
 * 根据编辑框声明的 [EditorInfo] 判断是否应关闭英文单词补全。
 */
object InputFieldPolicy {
    fun shouldSuppressAsciiSuggestions(editorInfo: EditorInfo): Boolean {
        val inputType = editorInfo.inputType
        val inputClass = inputType and InputType.TYPE_MASK_CLASS
        if (inputClass == InputType.TYPE_NULL) return true
        if (editorInfo.imeOptions and EditorInfo.IME_FLAG_FORCE_ASCII != 0) return true
        if (inputClass != InputType.TYPE_CLASS_TEXT) return false
        if (inputType and InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS != 0) return true
        if (inputType and InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE != 0) return true
        return when (inputType and InputType.TYPE_MASK_VARIATION) {
            InputType.TYPE_TEXT_VARIATION_PASSWORD,
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
            InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD,
            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS,
            InputType.TYPE_TEXT_VARIATION_URI,
            InputType.TYPE_TEXT_VARIATION_FILTER -> true
            else -> false
        }
    }
}
