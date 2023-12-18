package com.test.roc.core.util;

public enum DateFormatEnum {
    yyyy_MM("yyyy-MM"),
    yyyy_MM_dd("yyyy-MM-dd"),
    yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"),
    MM_dd("MM-dd"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");


    DateFormatEnum(String format){
        this.format = format;
    }

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
