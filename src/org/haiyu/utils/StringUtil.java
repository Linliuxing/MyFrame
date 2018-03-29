package org.haiyu.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lyq on 2018/3/17.
 */

public class StringUtil {
    public  static  boolean isEmpty(String str){
        if(str!=null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public  static  boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static void main(String[] args){
        System.out.println(isEmpty("44"));
    }
}
