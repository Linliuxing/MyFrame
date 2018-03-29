package org.haiyu.helper;

import org.haiyu.annotation.Autowired;
import org.haiyu.bean.BeanContainer;
import org.haiyu.bean.BeanFactory;
import org.haiyu.utils.ArrayUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by lyq on 2018/3/21.
 */

public class IocHelper {
    static {

        Map<String,Object> beanContainer = BeanContainer.getBeanContainer();
        if(beanContainer!=null && beanContainer.size()>0){
            initIOC(beanContainer);
        }
    }

    private  static  void initIOC(Map<String,Object> beanContainer){

        for (Map.Entry<String,Object> entry:beanContainer.entrySet()){

            String calassName = entry.getKey();
            Object beanInstance = entry.getValue();
            Class<?> beanClass= null;

            try {
                beanClass=  Class.forName(calassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Field[] beanFileds = beanClass.getDeclaredFields();
            if(ArrayUtil.isNotEmpty(beanFileds)){
                for (Field f:beanFileds){
                    if(f.isAnnotationPresent(Autowired.class)){
                        Class<?> beanFieldClass = f.getType();
                        Object beanFieldInstance  = beanContainer.get(beanFieldClass.getName());
                        if(beanFieldInstance!=null){
                            BeanFactory.setField(beanInstance,f,beanFieldInstance);
                        }
                    }

                }

            }
        }
    }
}
