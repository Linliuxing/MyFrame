package org.haiyu.helper;

import org.haiyu.annotation.Controller;
import org.haiyu.annotation.Service;
import org.haiyu.utils.ClassUtil;
import org.haiyu.utils.PropsUtil;

import java.util.ArrayList;

/**
 * Created by lyq on 2018/3/21.
 */

public class Classhelper {
    public static ArrayList<Class<?>> getCalasses() {
        return calasses;
    }

    public static void setCalasses(ArrayList<Class<?>> calasses) {
        Classhelper.calasses = calasses;
    }

    public static ArrayList<Class<?>> calasses;

    static {
        String basePackeage= PropsUtil.getString("app.base_package");
        calasses = ClassUtil.getClasses(basePackeage);
    }

    /**
     * 获取所以sevice 的类
     * @return
     */
    public static ArrayList<Class<?>> getServiceClasses(){
        ArrayList<Class<?>> sc = new ArrayList<>();
        for(Class<?> c:calasses){
            if(c.isAnnotationPresent(Service.class)){
                sc.add(c);
            }
        }
        return  sc;
    }

    /**
     * 获取controller类
     * @return
     */
    public static ArrayList<Class<?>> getControllerClasses(){
        ArrayList<Class<?>> cc = new ArrayList<>();
        for(Class<?> c:calasses){
            if(c.isAnnotationPresent(Controller.class)){
                cc.add(c);
            }
        }
        return  cc;
    }

    /**
     * 获取所以的类
     * @return
     */
    public static ArrayList<Class<?>> getBenClasses(){
        ArrayList<Class<?>> bc = new ArrayList<>();
        bc.addAll(getServiceClasses());
        bc.addAll(getControllerClasses());
        return  bc;
    }

}
