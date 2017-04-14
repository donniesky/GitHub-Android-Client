package me.donnie.github.data.entity;

import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class Result<T> {

    private String next;

    private String last;

    private List<T> items;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
