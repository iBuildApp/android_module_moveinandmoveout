package com.ibuildapp.moveinandmoveout.model;

import android.graphics.Color;

/**
 * Created by web-developer on 16.03.2017.
 */

public class ColorSkin {
    private static final String IS_LIGHT_VALUE_LIGHT = "1";

    private final int color1;
    private final int color2;
    private final int color3;
    private final int color4;
    private final int color5;

    private final boolean light;

    private ColorSkin(Builder builder) {
        color1 = builder.color1;
        color2 = builder.color2;
        color3 = builder.color3;
        color4 = builder.color4;
        color5 = builder.color5;

        light = builder.light;
    }

    public int getColor1() {
        return color1;
    }

    public int getColor2() {
        return color2;
    }

    public int getColor3() {
        return color3;
    }

    public int getColor4() {
        return color4;
    }

    public int getColor5() {
        return color5;
    }


    public boolean isLight() {
        return light;
    }

    public static class Builder {

        private int color1;
        private int color2;
        private int color3;
        private int color4;
        private int color5;

        private boolean light;

        public Builder setColor1(String color1) {
            this.color1 = Color.parseColor(color1);

            return this;
        }

        public Builder setColor2(String color2) {
            this.color2 = Color.parseColor(color2);

            return this;
        }

        public Builder setColor3(String color3) {
            this.color3 = Color.parseColor(color3);

            return this;
        }

        public Builder setColor4(String color4) {
            this.color4 = Color.parseColor(color4);

            return this;
        }

        public Builder setColor5(String color5) {
            this.color5 = Color.parseColor(color5);

            return this;
        }

        public Builder setLight(String light) {
            this.light = IS_LIGHT_VALUE_LIGHT.equals(light);

            return this;
        }

        public ColorSkin build() {
            return new ColorSkin(this);
        }

    }
}
