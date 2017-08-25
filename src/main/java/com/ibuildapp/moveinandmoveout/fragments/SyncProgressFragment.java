package com.ibuildapp.moveinandmoveout.fragments;


import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.appbuilder.sdk.android.animations.SimpleAnimatorListener;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.OnCompletedListener;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.util.ArrayList;
import java.util.List;

public class SyncProgressFragment extends Fragment {
    private List<View> views;
    private int animationPosition = 0;
    private int[] animationValues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_sync_progress, container, false);

        views = new ArrayList<>();

        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_one));
        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_two));
        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_three));
        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_four));
        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_five));
        views.add(fragment.findViewById(R.id.moveinandmoveout_sync_progress_six));

        animationValues = new int[]{17, 34, 51, 68, 85, 100 };

        return fragment;
    }

    public synchronized void updateTo(Integer value){
        if (animationPosition < animationValues.length && value >= animationValues[animationPosition]) {
            checkAndSetVisibility();
            animateView(views.get(animationPosition), value);
        }
    }

    public void updateToLast(OnCompletedListener listener){
        if (animationPosition < animationValues.length && 101 >= animationValues[animationPosition]) {
            animateViewToLast(views.get(animationPosition), listener);
            checkAndSetVisibility();
        }else listener.onCompleted();
    }
    private void animateViewToLast(final View view, final OnCompletedListener listener) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            view.setScaleX(0);
            view.setScaleY(0);
            view.animate().scaleY(1)
                    .scaleX(1)
                    .setStartDelay(100)
                    .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                    .setInterpolator(new OvershootInterpolator())
                    .setListener(new SimpleAnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            view.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationPosition++;
                            updateToLast(listener);
                        }
                    });
        } else {
            animationPosition++;
            view.setVisibility(View.VISIBLE);
        }
    }

    private void animateView(final View view, final Integer value){
        if (android.os.Build.VERSION.SDK_INT >= 14){
            view.setScaleX(0);
            view.setScaleY(0);
            view.animate().scaleY(1)
                    .scaleX(1)
                    .setStartDelay(100)
                    .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                    .setInterpolator(new OvershootInterpolator())
                    .setListener(new SimpleAnimatorListener(){
                        @Override
                        public void onAnimationStart(Animator animation) {
                            view.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationPosition++;
                            updateTo(value);
                        }
                    });
        }else {
            animationPosition++;
            view.setVisibility(View.VISIBLE);
        }
    }

    public void checkAndSetVisibility(){
        for (int index = 0; index < animationPosition; index++){
            View currentView = views.get(index);
            if (currentView.getVisibility() != View.VISIBLE)
                currentView.setVisibility(View.VISIBLE);
        }
    }
}

