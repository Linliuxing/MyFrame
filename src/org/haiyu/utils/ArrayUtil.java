package org.haiyu.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by lyq on 2018/3/17.
 */

public class ArrayUtil {
    public  static  boolean isEmpty(Object[] list){
        return ArrayUtils.isEmpty(list);
    }

    public  static  boolean isNotEmpty(Object[] list){
        return !isEmpty(list);
    }
}

