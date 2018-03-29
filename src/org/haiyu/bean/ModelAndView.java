package org.haiyu.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyq on 2018/3/21.
 */

public class ModelAndView {
    public String getPath() {
        return path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    private  String path;

    private Map<String,Object> data;

    public ModelAndView(String path){

        this.path = path;
        data = new HashMap<>();
    }

    public  ModelAndView addData(String key,Object value){
        data.put(key,value);
        return this;
    }
}
