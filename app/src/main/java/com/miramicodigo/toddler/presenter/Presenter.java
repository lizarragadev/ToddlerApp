package com.miramicodigo.toddler.presenter;

/**
 * Created by gusn8 on 30-01-17.
 */

public interface Presenter<T> {
    void addView(T view);
    void removeView();
}
