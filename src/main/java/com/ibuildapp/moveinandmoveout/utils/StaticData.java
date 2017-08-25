package com.ibuildapp.moveinandmoveout.utils;
import com.ibuildapp.moveinandmoveout.xml.XmlParser;

public class StaticData {
    private static XmlParser.ParseResult xmlParsedData;
    private static int widgetId;

    public static void setXmlParsedData(XmlParser.ParseResult xmlParsedData) {
        StaticData.xmlParsedData = xmlParsedData;
    }

    public static XmlParser.ParseResult getXmlParsedData() {
        return xmlParsedData;
    }

    public static void setWidgetId(int widgetId) {
        StaticData.widgetId = widgetId;
    }

    public static int getWidgetId() {
        return widgetId;
    }
}
