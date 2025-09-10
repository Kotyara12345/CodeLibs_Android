package com.codelibs.core_ui.utils

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

fun String.fromHtmlToAnnotatedString(): AnnotatedString {
    val spanned: Spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)

    return buildAnnotatedString {
        var i = 0
        while (i < spanned.length) {
            val next = spanned.nextSpanTransition(i, spanned.length, Any::class.java)
            var chunk = spanned.subSequence(i, next).toString()

            // собираем стили
            val styles = mutableListOf<SpanStyle>()
            var isBullet = false
            spanned.getSpans(i, next, Any::class.java).forEach { span ->
                when (span) {
                    is android.text.style.StyleSpan -> {
                        when (span.style) {
                            android.graphics.Typeface.BOLD ->
                                styles.add(SpanStyle(fontWeight = FontWeight.Bold))

                            android.graphics.Typeface.ITALIC ->
                                styles.add(SpanStyle(fontStyle = FontStyle.Italic))

                            android.graphics.Typeface.BOLD_ITALIC ->
                                styles.add(
                                    SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic
                                    )
                                )
                        }
                    }

                    is android.text.style.BulletSpan -> {
                        isBullet = true
                    }
                }
            }

            // чистим начало, если это новая строка или буллит
            if (chunk.startsWith("\n")) {
                chunk = "\n" + chunk.trimStart()
            } else if (isBullet) {
                chunk = chunk.trimStart()
            }

            // если это буллит, подставляем символ
            if (isBullet && chunk.isNotBlank()) {
                append("• ")
            }

            // применяем стили к куску
            if (styles.isNotEmpty()) {
                withStyle(styles.reduce { acc, style -> acc.merge(style) }) {
                    append(chunk)
                }
            } else {
                append(chunk)
            }
            i = next
        }
    }
}