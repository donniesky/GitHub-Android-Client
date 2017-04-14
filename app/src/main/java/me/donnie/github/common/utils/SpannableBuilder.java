package me.donnie.github.common.utils;

import android.support.annotation.ColorInt;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;

import static android.graphics.Typeface.BOLD;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public class SpannableBuilder extends SpannableStringBuilder {

    private SpannableBuilder() {}

    public static SpannableBuilder builder() {
        return new SpannableBuilder();
    }

    public SpannableBuilder append(final CharSequence text, final Object span) {
        if (!TextUtils.isEmpty(text)) {
            append(text);
            if (span != null) {
                final int length = length();
                setSpan(span, length - text.length(), length, SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return this;
    }

    @Override public SpannableBuilder append(char text) {
        if (text != 0) super.append(text);
        return this;
    }

    @Override public SpannableBuilder append(CharSequence text) {
        if (text != null) super.append(text);
        return this;
    }

    public SpannableBuilder append(final char text, final Object span) {
        append(text);
        if (!TextUtils.isEmpty(span.toString())) {
            final int length = length();
            setSpan(span, length - 1, length, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return this;
    }

    public SpannableBuilder bold(final CharSequence text) {
        if (!TextUtils.isEmpty(text)) return append(text, new StyleSpan(BOLD));
        return this;
    }

    public SpannableBuilder background(final CharSequence text, final int color) {
        if (!TextUtils.isEmpty(text))
            return append(text, new BackgroundColorSpan(color));
        return this;
    }

    public SpannableBuilder foreground(final CharSequence text, @ColorInt final int color) {
        if (!TextUtils.isEmpty(text)) return append(text, new ForegroundColorSpan(color));
        return this;
    }

    public SpannableBuilder foreground(final char text, @ColorInt final int color) {
        return append(text, new ForegroundColorSpan(color));
    }

    public SpannableBuilder url(final CharSequence text, final View.OnClickListener listener) {
        if (!TextUtils.isEmpty(text))
            return append(text, new URLSpan(text.toString()) {
                @Override
                public void onClick(View widget) {
                    listener.onClick(widget);
                }
            });
        return this;
    }

    public SpannableBuilder url(final CharSequence text) {
        if (!TextUtils.isEmpty(text)) return append(text, new URLSpan(text.toString()));
        return this;
    }

}
