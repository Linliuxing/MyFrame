package org.haiyu.bean;

import org.haiyu.annotation.Controller;
import org.haiyu.annotation.Service;
import org.haiyu.helper.Classhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyq on 2018/3/21.
 */

public class BeanContainer {
    private  static  final Map<String,Object> beanContainer = new HashMap<>();

    static {
        ArrayList<Class<?>> classes = Classhelper.getBenClasses();
        for (Class<?> beanClass: classes){

            if(beanClass.isAnnotationPresent(Controller.class)||
                    beanClass.isAnnotationPresent(Service.class)){
                Object object = BeanFactory.newInstance(beanClass);
                beanContainer.put(beanClass.getName(),object);
            }
        }
    }
    public static Map<String, Object> getBeanContainer() {
        return beanContainer;
    }

    /**
     * 从实例化的容器获取bean
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T getBean(String className){
        if(!beanContainer.containsKey(className)){
            throw  new RuntimeException("不能获取到该类");
        }
        return (T) beanContainer.get(className);

    }
}
