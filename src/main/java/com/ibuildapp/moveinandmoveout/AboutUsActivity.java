package com.ibuildapp.moveinandmoveout;


import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.bumptech.glide.Glide;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.xml.XmlParser;
import com.restfb.util.StringUtils;

public class AboutUsActivity extends AppBuilderModuleMainAppCompat {

    private ImageView titleImage;
    private TextView titleText;
    private TextView descriptionText;

    private ScrollView root;

    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_about_us);

        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarTitle(getString(R.string.moveinandmoveout_about_us));
        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.moveinandmoveout_back), getResources().getColor(android.R.color.black), true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTopBarTitleColor(ContextCompat.getColor(this, android.R.color.black));

        root = (ScrollView) findViewById(R.id.moveinandmoveout_about_us_root);
        root.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        titleImage = (ImageView) findViewById(R.id.moveinandmoveout_about_us_image);
        titleText = (TextView) findViewById(R.id.moveinandmoveout_about_us_title);
        descriptionText = (TextView) findViewById(R.id.moveinandmoveout_about_us_description);

        XmlParser.ParseResult.About about = StaticData.getXmlParsedData().getAbout();

        if (!StringUtils.isBlank(about.getLogo())) {
            titleImage.setVisibility(View.VISIBLE);
            Glide.with(this).load(about.getLogo()).into(titleImage);
        }else titleImage.setVisibility(View.GONE);

        titleText.setText(about.getTitle());
        titleText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());

        descriptionText.setText(about.getDescription());
        descriptionText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }
}
