package com.ibuildapp.moveinandmoveout.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class DetailsScrollView extends ScrollView{
    private boolean scrollEnabled = true;
    public DetailsScrollView(Context context) {
        super(context);
    }

    public DetailsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollEnabled && super.onTouchEvent(ev);
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.scrollEnabled = scrollEnabled;
    }
}
