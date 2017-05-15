package com.ibuildapp.moveinandmoveout.xml;


import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;
import com.ibuildapp.moveinandmoveout.model.ColorSkin;

import com.google.gson.Gson;
import com.ibuildapp.moveinandmoveout.model.MoveMapper;
import com.ibuildapp.moveinandmoveout.model.PropertyMapper;

public class XmlParser {
    private static final String ERROR_XML_PARSING = "Xml parsing error";
    private static final String TAG = XmlParser.class.getSimpleName();

    private static final String XML_DATA = "data";
    private static final String XML_CONFIG = "config";
    private static final String XML_COLOR_SKIN = "colorskin";
    private static final String XML_COLOR_SKIN_COLOR_1 = "color1";
    private static final String XML_COLOR_SKIN_COLOR_2 = "color2";
    private static final String XML_COLOR_SKIN_COLOR_3 = "color3";
    private static final String XML_COLOR_SKIN_COLOR_4 = "color4";
    private static final String XML_COLOR_SKIN_COLOR_5 = "color5";
    private static final String XML_COLOR_SKIN_IS_LIGHT = "isLight";
    private static final String XML_SPLASH_SCREEN = "splashscreen";

    private static final String XML_ABOUT = "about";
    private static final String XML_ABOUT_LOGO = "logo";
    private static final String XML_ABOUT_TITLE = "title";
    private static final String XML_ABOUT_DESCRIPTION = "description";

    private static final String XML_PROPERTYMAP = "propertymap";
    private static final String XML_MOVEMAP = "movemap";
    private static final String XML_GOOGLE = "google";
    private static final String XML_ACCESS_TOKEN = "access_token";
    private static final String XML_DOCUMENT_TOKEN = "document_token";
    private static final String XML_PROPERTYSPREADSHEET_ID = "propertyworksheet_id";
    private static final String XML_MOVESPREEDSHEET_id = "moveworksheet_id";
    private static final String XML_PROPERTYFIRST_ROW = "propertyfirst_row";
    private static final String XML_MOVEFIRST_ROW = "movefirst_row";

    public static ParseResult parse(String xmlString){
        final ParseResult.Builder parseResultBuilder = new ParseResult.Builder();
        final ColorSkin.Builder colorSkinBuilder = new ColorSkin.Builder();

        RootElement data = new RootElement(XML_DATA);
        Element config = data.getChild(XML_CONFIG);
        Element colorSkin = config.getChild(XML_COLOR_SKIN);

        Element splashScreen = data.getChild(XML_SPLASH_SCREEN);
        Element propertymap = data.getChild(XML_PROPERTYMAP);
        Element movemap = data.getChild(XML_MOVEMAP);

        Element google = data.getChild(XML_GOOGLE);
        final Element about = data.getChild(XML_ABOUT);

        final ParseResult.Google googleModel = new ParseResult.Google();
        final ParseResult.About aboutModel = new ParseResult.About();

        colorSkin.getChild(XML_COLOR_SKIN_COLOR_1).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setColor1(body.trim());
            }
        });

        colorSkin.getChild(XML_COLOR_SKIN_COLOR_2).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setColor2(body.trim());
            }
        });

        colorSkin.getChild(XML_COLOR_SKIN_COLOR_3).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setColor3(body.trim());
            }
        });

        colorSkin.getChild(XML_COLOR_SKIN_COLOR_4).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setColor4(body.trim());
            }
        });

        colorSkin.getChild(XML_COLOR_SKIN_COLOR_5).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setColor5(body.trim());
            }
        });

        colorSkin.getChild(XML_COLOR_SKIN_IS_LIGHT).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                colorSkinBuilder.setLight(body.trim());
                parseResultBuilder.setColorSkin(colorSkinBuilder.build());
            }
        });

        splashScreen.setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                parseResultBuilder.setSplashScreenUrl(body.trim());
            }
        });

        propertymap.setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                PropertyMapper propmapper = new Gson().fromJson(body.trim(), PropertyMapper.class);
                parseResultBuilder.setJsonMapProp(propmapper);
            }
        });

        movemap.setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                MoveMapper movemapper = new Gson().fromJson(body.trim(), MoveMapper.class);
                parseResultBuilder.setJsonMapMove(movemapper);
            }
        });

        google.getChild(XML_ACCESS_TOKEN).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setAccessToken(body.trim());
            }
        });

        google.getChild(XML_DOCUMENT_TOKEN).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setDocumentToken(body.trim());
            }
        });

        google.getChild(XML_PROPERTYSPREADSHEET_ID).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setSheetIdProp(body.trim());
            }
        });
        google.getChild(XML_MOVESPREEDSHEET_id).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setSheetIdMove(body.trim());
            }
        });
        google.getChild(XML_PROPERTYFIRST_ROW).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setFirstRowProp(Integer.valueOf(body.trim()));
            }
        });
        google.getChild(XML_MOVEFIRST_ROW).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                googleModel.setFirstRowMove(Integer.valueOf(body.trim()));
            }
        });

        google.setEndElementListener(new EndElementListener() {
            @Override
            public void end() {
                parseResultBuilder.setGoogle(googleModel);
            }
        });

        about.getChild(XML_ABOUT_LOGO).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                aboutModel.setLogo(body.trim());
            }
        });

        about.getChild(XML_ABOUT_TITLE).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                aboutModel.setTitle(body.trim());
            }
        });

        about.getChild(XML_ABOUT_DESCRIPTION).setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                aboutModel.setDescription(body.trim());
            }
        });

        about.setEndElementListener(new EndElementListener() {
            @Override
            public void end() {
                parseResultBuilder.setAbout(aboutModel);
            }
        });

        try {
            Xml.parse(xmlString, data.getContentHandler());
        } catch(Exception exception) {
            Log.e( TAG, ERROR_XML_PARSING, exception);
        }
        return parseResultBuilder.build();
    }

    public static class ParseResult {
        public static final class Google{
            private String accessToken;
            private String documentToken;
            private String sheetIdProp;
            private String sheetIdMove;
            private Integer firstRowProp;
            private Integer firstRowMove;

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public String getDocumentToken() {
                return documentToken;
            }

            public void setDocumentToken(String documentToken) {
                this.documentToken = documentToken;
            }

            public String getSheetIdProp() {
                return sheetIdProp;
            }

            public void setSheetIdProp(String sheetIdProp) {
                this.sheetIdProp = sheetIdProp;
            }

   /*         public void setSheetIdProp(String sheetIdProp) {
                this.sheetIdProp = "1146386248";
            }*/
            public String getSheetIdMove() {
                return sheetIdMove;
            }

            public void setSheetIdMove(String sheetIdMove) {
                this.sheetIdMove = sheetIdMove;
            }

            public Integer getFirstRowProp() {
                return firstRowProp;
            }

            public void setFirstRowProp(Integer firstRowProp) {
                this.firstRowProp = firstRowProp;
            }

            public Integer getFirstRowMove() {
                return firstRowMove;
            }

            public void setFirstRowMove(Integer firstRowMove) {
                this.firstRowMove = firstRowMove;
            }



        }
        public static class About{
            private String logo;
            private String title;
            private String description;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        private final ColorSkin colorSkin;
        private final String splashScreenUrl;
        private final PropertyMapper jsonMapProp;
        private final MoveMapper jsonMapMove;
        private final Google google;
        private About about;

        private ParseResult(Builder builder) {
            colorSkin = builder.colorSkin;
            splashScreenUrl = builder.getSplashScreenUrl();
            about = builder.getAbout();
            jsonMapProp = builder.getJsonMapProp();
            jsonMapMove = builder.getJsonMapMove();
            google = builder.getGoogle();
        }

        public ColorSkin getColorSkin() {
            return colorSkin;
        }

        public String getSplashScreenUrl() {
            return splashScreenUrl;
        }

        public PropertyMapper getJsonMapProp() {
            return jsonMapProp;
        }

        public MoveMapper getJsonMapMove() {
            return jsonMapMove;
        }

        public Google getGoogle() {
            return google;
        }

        public About getAbout() {
            return about;
        }

        public static class Builder {
            private ColorSkin colorSkin = new ColorSkin.Builder().build();
            private String splashScreenUrl;
            private About about;
            private PropertyMapper jsonMapProp;
            private MoveMapper jsonMapMove;
            private Google google;


            public PropertyMapper getJsonMapProp() {
                return jsonMapProp;
            }

            public void setJsonMapProp(PropertyMapper jsonMapProp) {
                this.jsonMapProp = jsonMapProp;
            }

            public MoveMapper getJsonMapMove() {
                return jsonMapMove;
            }

            public void setJsonMapMove(MoveMapper jsonMapMove) {
                this.jsonMapMove = jsonMapMove;
            }


            public String getSplashScreenUrl() {
                return splashScreenUrl;
            }

            public Builder setSplashScreenUrl(String splashScreenUrl) {
                this.splashScreenUrl = splashScreenUrl;

                return this;
            }

            public ColorSkin getColorSkin() {
                return colorSkin;
            }

            public Builder setColorSkin(ColorSkin colorSkin) {
                this.colorSkin = colorSkin;

                return this;
            }

            public ParseResult build() {
                return new ParseResult(this);
            }

            public Google getGoogle() {
                return google;
            }

            public Builder setGoogle(Google google) {
                this.google = google;

                return this;
            }

            public About getAbout() {
                return about;
            }

            public Builder setAbout(About about) {
                this.about = about;
                return this;
            }
        }
    }

}
