package com.ibuildapp.moveinandmoveout.fragments.details;

/**
 * Created by web-developer on 15.04.2017.
 */

import android.animation.Animator;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbuilder.sdk.android.animations.SimpleAnimatorListener;
import com.bumptech.glide.Glide;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.BitmapUtils;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.view.DrawingView;

public class SignatureHolder {
    public interface SignatureHolderListener{
        void lockPressed();
        void unlockPressed(byte[] newSignature);
        void justUnlock();
    }

    private ImageView signatureView;
    private TextView signatureValue;
    private TextView signatureClear;
    private DrawingView paintView;
    private View imageLayout;
    private ImageView backImage;

    public View lockButton;
    private View signatureRoot;

    private boolean lockSignature = true;
    private boolean clearClicked = false;

    private SignatureHolderListener listener;

    public SignatureHolder(View fragment){
        signatureValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_text);
        signatureView = (ImageView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_image);
        signatureClear = (TextView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_clear);
        paintView = (DrawingView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_paint);
        backImage = (ImageView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_back_image);

        imageLayout = fragment.findViewById(R.id.moveinandmoveout_edit_signature_image_layout);
        signatureRoot = fragment.findViewById(R.id.moveinandmoveout_edit_signature_root_layout);

        imageLayout.setDrawingCacheEnabled(true);

        signatureClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClicked = true;
                paintView.clear();
            }
        });
        lockButton = fragment.findViewById(R.id.moveinandmoveout_edit_signature_lock_button);
    }


    public void initColorScheme(){
        signatureValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        signatureClear.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

        if (StaticData.getXmlParsedData().getColorSkin().isLight())
            signatureView.setImageResource(R.drawable.moveinandmoveout_unlock_black);
        else signatureView.setImageResource(R.drawable.moveinandmoveout_unlock_white);
    }

    public void clear(){
        paintView.clear();
        clearClicked = false;
    }

    public void setListener(SignatureHolderListener listener) {
        this.listener = listener;
    }

    public void initData(SpreadsheetItemMove cloneItem){
        if (cloneItem.getSignature()!= null)
            Glide.with(signatureView.getContext()).load(cloneItem.getSignature()).into(backImage);

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lockSignature){
                    if (StaticData.getXmlParsedData().getColorSkin().isLight())
                        signatureView.setImageResource(R.drawable.moveinandmoveout_unlock_black);
                    else signatureView.setImageResource(R.drawable.moveinandmoveout_unlock_white);
                    signatureValue.setText(R.string.moveinandmoveout_signature_unlock);

                    paintView.setPaintEnabled(false);
                    if (paintView.isViewTouched()) {
                        listener.unlockPressed(BitmapUtils.getBytes(imageLayout.getDrawingCache()));
                    }else if (clearClicked)
                        listener.unlockPressed(null);
                    else listener.justUnlock();

                    GradientDrawable drawable = (GradientDrawable) signatureRoot.getBackground();
                    drawable.setStroke((int) signatureRoot.getResources().getDimension(R.dimen.moveinandmoveout_signature_unlock)
                            ,signatureRoot.getResources().getColor(R.color.moveinandmoveout_edit_separator_color));
                    hideButton(signatureClear);

                }else {
                    if (StaticData.getXmlParsedData().getColorSkin().isLight())
                        signatureView.setImageResource(R.drawable.moveinandmoveout_lock_black);
                    else signatureView.setImageResource(R.drawable.moveinandmoveout_lock_white);
                    signatureValue.setText(R.string.moveinandmoveout_signature_lock);

                    paintView.setPaintEnabled(true);
                    listener.lockPressed();

                    GradientDrawable drawable = (GradientDrawable) signatureRoot.getBackground();
                    drawable.setStroke((int) signatureRoot.getResources().getDimension(R.dimen.moveinandmoveout_signature_lock)
                            ,StaticData.getXmlParsedData().getColorSkin().getColor5());

                    showButton(signatureClear);
                }

                lockSignature = !lockSignature;
            }
        });
    }

    protected void showButton(final View clearText) {
        if (clearText.getVisibility()!= View.VISIBLE)
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                clearText.setScaleX(0);
                clearText.setScaleY(0);

                clearText.animate().scaleX(1).scaleY(1)
                        .setDuration(MoveInandMoveOutContants.ANIMATION_DURATION)
                        .setInterpolator(new OvershootInterpolator(2f))
                        .setListener(new SimpleAnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                clearText.setVisibility(View.VISIBLE);
                            }
                        });
            } else clearText.setVisibility(View.VISIBLE);
    }

    protected void hideButton(final View clearText) {
        if (android.os.Build.VERSION.SDK_INT >= 12) {

            clearText.animate().scaleX(0).scaleY(0)
                    .setDuration(MoveInandMoveOutContants.ANIMATION_DURATION)
                    .setInterpolator(new AccelerateInterpolator(2f))
                    .setListener(new SimpleAnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            clearText.setVisibility(View.GONE);
                        }
                    });
        } else clearText.setVisibility(View.GONE);
    }
}