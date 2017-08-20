package com.alexanderplyaka.weatherexchangerate.widget;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

// Реализация шаблона "Pull to Refresh", когда пользователь сдвигает экран, чтобы обновить данные.
public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    private int scaledTouchSlop;
    private float prevX;
    private boolean declined;

    public VerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Возвращает конфигурацию для указанного контекста.
        // getScaledTouchSlop() Возвращает расстояние в пикселях, при касании может варьироватся, прежде чем мы определим, что пользователь прокручивает
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    // Перехват всех событий движения сенсорного экрана
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                // obtain создайт новое событие движения, копируя его из существующего.
                // Метод getX дает X координаты касания
                prevX = MotionEvent.obtain(event).getX();
                declined = false; // New action
                break;
            case MotionEvent.ACTION_MOVE: // движение
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - prevX);
                if(declined || xDiff > scaledTouchSlop){
                    declined = true; // Запоминает
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
