package me.donnie.github.data.entity;

import android.support.annotation.StringRes;

import me.donnie.github.R;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public enum StateType {

    open(R.string.open),
    closed(R.string.closed);

    @StringRes
    int type;

    StateType(@StringRes int type) {
        this.type = type;
    }

    @StringRes
    public int getType() {
        return type;
    }
}
