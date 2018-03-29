package org.haiyu.controller;

import org.haiyu.annotation.Service;

/**
 * Created by lyq on 2018/3/29.
 */
@Service
public class HomeService {
    public String getHome(){
        return "Hello";
    }
}
