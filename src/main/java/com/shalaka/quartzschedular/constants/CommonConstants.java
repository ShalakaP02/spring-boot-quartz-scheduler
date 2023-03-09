package com.shalaka.quartzschedular.constants;

import java.text.SimpleDateFormat;

public final class CommonConstants {

    private CommonConstants(){
    }

    public static final String DATE_FORMAT_COMMON = "yyyy-MM-dd";
    private static SimpleDateFormat sdf;

    public static SimpleDateFormat getSimpleDateFormat(){
        if(sdf == null){
            sdf  = new SimpleDateFormat(DATE_FORMAT_COMMON);
        }
        return sdf;
    }




}
